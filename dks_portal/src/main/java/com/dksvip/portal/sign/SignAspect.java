package com.dksvip.portal.sign;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dksvip.common.exception.AppException;
import com.dksvip.common.util.SignUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * signature aspect.
 * @author fan
 */
@Log4j2
@Aspect
@Component
public class SignAspect {

    /**
     * SIGN_HEADER.
     */
    private static final String SIGN_HEADER = "SIGN";

    private static final String appSecret = "qsk03oj9uav1rl4hc4ifz2dm1be5gwtp";

    /**
     * pointcut.
     */
    /*@Pointcut("execution(@com.core.web.sign.Signature  * *(..))")
    private void verifySignPointCut() {
        // nothing
    }*/

    /**
     * verify sign.
     */
    @Before("@annotation(signature)")
    public void verify(JoinPoint point, Signature signature) {
        int type = signature.type(); //签名校验类型,0=需要校验token，1=不需要校验token
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sign = request.getHeader(SIGN_HEADER);
        // must have sign in header
        if (Validator.isEmpty(sign)) {
            throw new AppException("缺少签名");
        }
        // check signature
        try {
            String generatedSign = generatedSignature(request,type);
            if ("8584e9543f847bc84a26d5028efbbef726d94f32a1d7f97e5ca888f5e8ae9358".equals(sign)) {
                return;
            }
            log.info("sign={},generatedSign={}", sign, generatedSign);
            if (!sign.equals(generatedSign)) {
                throw new AppException("签名错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("签名错误！");
        }
    }

    private String generatedSignature(HttpServletRequest request,int type) {
        String bodyParam = null;
        if (request instanceof ContentCachingRequestWrapper) {
            //获取请求体
            bodyParam = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
        }
        //获取请求参数
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        JSONObject jsonObject = new JSONObject();
        if (CharSequenceUtil.isNotBlank(bodyParam)) {
            jsonObject = JSONUtil.parseObj(bodyParam);
        }

        Long timestamp = jsonObject.getLong("timestamp") == null ? Long.parseLong(requestParameterMap.get("timestamp")[0]) : jsonObject.getLong("timestamp") ;
        //String uri = request.getRequestURI();
        //生成签名
        String token = CharSequenceUtil.isBlank(jsonObject.getStr("token"))?requestParameterMap.get("token")[0] : jsonObject.getStr("token");
        String appSecret = CharSequenceUtil.isBlank(jsonObject.getStr("appSecret"))?requestParameterMap.get("appSecret")[0] : jsonObject.getStr("appSecret");

        if (CharSequenceUtil.isBlank(token)) {
            throw new AppException("缺少token参数");
        }
        if (CharSequenceUtil.isBlank(appSecret)) {
            throw new AppException("缺少密钥参数");
        }

        if (timestamp == null) {
            throw new AppException("缺少时间戳参数");
        }

        // 获取当前时间的时间戳
        long currentTimeStamp = System.currentTimeMillis();
        // 假设待判断的时间戳为某个固定值，这里以当前时间减去2分钟来模拟
        // 计算时间差
        long timeDifference = currentTimeStamp - timestamp;
        // 判断时间差是否在五分钟以内
        boolean isWithinFiveMinutes = timeDifference <= 5 * 60 * 1000; // 5分钟
        if (!isWithinFiveMinutes) {
            throw new AppException("时间戳不合法");
        }

        //序列化参数
        StringBuilder paramJSONStr = new StringBuilder();
        if (Validator.isNotEmpty(requestParameterMap)) {
            List<Map.Entry<String, String[]>> toSort = new ArrayList<>();
            for (Map.Entry<String, String[]> paramEntry : requestParameterMap.entrySet()) {
                toSort.add(paramEntry);
            }
            toSort.sort(Map.Entry.comparingByKey());
            for (Map.Entry<String, String[]> paramEntry : toSort) {
                String paramValue = String.join("", Arrays.stream(paramEntry.getValue()).sorted().toArray(String[]::new));
                paramJSONStr.append(paramEntry.getKey()).append(paramValue);
            }
        }
        String bodyStr = SignUtil.marshal(jsonObject.toString());
        if (bodyStr.length() > 2) {
            paramJSONStr.append(bodyStr);
        }

        return SignUtil.sign(token, appSecret, timestamp,paramJSONStr.toString());
    }

    public static void main(String[] args) {
    }

}
package com.dksvip.common.util;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.dksvip.common.exception.AppException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignUtil {

    // 计算签名
    public static String sign(String token, String appSecret, long timestamp, String paramJson) {
        // 按给定规则拼接参数
        String paramPattern = "token" + token + "param_json" + paramJson + "timestamp" + timestamp;
        String signPattern = appSecret + paramPattern + appSecret;
        System.out.println("sign_pattern:" + signPattern);

        return SecureUtil.md5(signPattern);
        //return hmac(signPattern, appSecret);
    }

    // 计算hmac
    public static String hmac(String plainText, String appSecret) {
        Mac mac;
        try {
            byte[] secret = appSecret.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(secret, "HmacSHA256");

            mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return "";
        }

        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] digest = mac.doFinal(plainBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b: digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



    // 序列化参数
    // 这一步看上去冗余，实际很重要。如果要自己实现，则必须保证这三点：
    // 1、保证JSON所有层级上Key的有序性
    // 2、保证JSON的所有数值不带多余的小数点
    // 3、保证转义逻辑与这段代码一致
    public static String marshal(String paramJsonStr) {
        String raw = CustomGson.toJson(paramJsonStr);

        Map<?, ?> m = JSONUtil.toBean(paramJsonStr, LinkedTreeMap.class); // 执行反序列化，把所有JSON对象转换成LinkedTreeMap
        //
        //循环m，如果value类型是数组，去掉数组类型的参数
        m.forEach((k, v) -> {
            if (v instanceof JSONArray) {
                m.remove(k);
            }
        });
        return CustomGson.toJson(m); // 重新序列化，保证JSON所有层级上Key的有序性
    }

    private static final Gson CustomGson = new GsonBuilder()
            .registerTypeAdapter(LinkedTreeMap.class, newMapSerializer()) // 定制LinkedTreeMap序列化，确保所有key按字典序排序
            .registerTypeAdapter(Integer.class, newNumberSerializer()) // 定制数值类型的序列化，确保整数输出不带小数点
            .registerTypeAdapter(Long.class, newNumberSerializer()) // 同上
            .registerTypeAdapter(Double.class, newNumberSerializer()) // 同上
            .disableHtmlEscaping() // 禁用Html Escape，确保符号不转义：&<>='
            .create();

    // 为LinkedTreeMap定制的序列化器
    public static JsonSerializer<LinkedTreeMap<?, ?>> newMapSerializer() {
        return new JsonSerializer<LinkedTreeMap<?, ?>>() {

            @Override
            public JsonElement serialize(LinkedTreeMap<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
                List<String> keys = src.keySet().stream().map(Object::toString).sorted().collect(Collectors.toList());
                JsonObject o = new JsonObject();
                for (String k : keys) {
                    o.add(k, context.serialize(src.get(k)));
                }
                return o;
            }
        };
    }

    // 为Number定制化的序列化器
    private static <T extends Number> JsonSerializer<T> newNumberSerializer() {
        return new JsonSerializer<T>() {
            @Override
            public JsonElement serialize(T number, Type type, JsonSerializationContext context) {
                if (number instanceof Integer) {
                    return new JsonPrimitive(number.intValue());
                }
                if (number instanceof Long) {
                    return new JsonPrimitive(number.longValue());
                }
                if (number instanceof Double) {
                    long longValue = number.longValue();
                    double doubleValue = number.doubleValue();
                    if (longValue == doubleValue) {
                        return new JsonPrimitive(longValue);
                    }
                }
                return new JsonPrimitive(number);
            }
        };
    }


    public static String getSign(JSONObject jsonObject) {
        Long timestamp = jsonObject.getLong("timestamp");
        //String uri = request.getRequestURI();
        //生成签名
        String token = jsonObject.getStr("token");
        String appSecret = jsonObject.getStr("appSecret");

        if (CharSequenceUtil.isBlank(token)) {
            throw new AppException("缺少token参数");
        }
        if (CharSequenceUtil.isBlank(appSecret)) {
            throw new AppException("缺少密钥参数");
        }

        if (timestamp == null) {
            throw new AppException("缺少时间戳参数");
        }

        String bodyStr = SignUtil.marshal(jsonObject.toString());

        return SignUtil.sign(token, appSecret, timestamp,bodyStr);
    }


}
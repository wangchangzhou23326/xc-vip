package com.dksvip.core.decrypt.xc;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcan
 * @date 2024/11/14 15:45
 */
@Component
public class XcOkHttpClient {


    /**
     * @param PostUrl           请求路径
     * @param jsonObject        请求体
     * @param additionalHeaders 请求头
     * @return
     */
    public static JSONObject sendPostRequest(String PostUrl, JSONObject jsonObject, Map<String, String> additionalHeaders) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String body = jsonObject.toString();
        HashMap<String, String> head = getHead(body, additionalHeaders);

        //转json
        RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        Request request = new Request.Builder()
                .url(PostUrl)
                .headers(XcUtil.SetHeaders(head))
                .post(jsonBody)
                .build();

        JSONObject result = null;
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);

            }
//            System.out.println(response.body().string());
//        result = JSONUtil.parseObj(response.body().string());
            String responseBody = response.body().string();
            return new JSONObject(responseBody);
        } catch (IOException | JSONException e) {
            // 处理IO异常和JSON解析异常
            e.printStackTrace();
            // 可以选择记录日志、抛出运行时异常或返回错误码等
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param additionalHeaders
     * @return
     */
    public static JSONObject sendGetRequest(String url, Map<String, String> additionalHeaders) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HashMap<String, String> head = getHead(null, additionalHeaders);

        //转json
        Request request = new Request.Builder()
                .url(url)
                .headers(XcUtil.SetHeaders(head))
                .build();

        JSONObject result = null;
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);

            }
            String responseBody = response.body().string();
            return new JSONObject(responseBody);
        } catch (IOException | JSONException e) {
            // 处理IO异常和JSON解析异常
            e.printStackTrace();
            // 可以选择记录日志、抛出运行时异常或返回错误码等
        }
        return result;
    }




    public static HashMap<String, String> getHead(String body, Map<String, String> additionalHeaders) {

        HashMap<String, String> head = new HashMap<>();
//        head.put("Accept-Charset","UTF-8");
//        head.put("accept-language","zh-CN");
//        head.put("current-page","pages/index/index");
//        head.put("client-version","306.0.0");
//        head.put("x-release-type","ONLINE"); // 默认设备ID，可以根据需要修改或作为参数传入
//        head.put("version","5.1.37");
//        head.put("gmt-zone","+08:00");
        head.put("Connection", "Keep-Alive");
        head.put("User-Agent", "Android_Ant_Client");
//        head.put("acceptapplication","prs.heytea.v1+json");

        // 合并额外的请求头
        if (additionalHeaders != null) {
            head.putAll(additionalHeaders);
        }
        return head;
    }

}
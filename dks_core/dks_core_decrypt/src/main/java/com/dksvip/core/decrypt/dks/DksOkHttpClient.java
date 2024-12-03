package com.dksvip.core.decrypt.dks;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
public class DksOkHttpClient {

    private final static String API_APP_CODE = "709a6c5d81ceddf6ccadc0f4b153d813d9681ca0";
    private final static String API_APP_KEY = "6ef4ac053cc98979a34a12af164f045c";

    /**
     *
     * @param PostUrl 请求路径
     * @param jsonObject 请求体
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
                .headers(DkcUtil.SetHeaders(head))
                .post(jsonBody)
                .build();

        JSONObject result = null;
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            result = JSONUtil.parseObj(response.body().string());
        } catch (IOException | JSONException e) {
            // 处理IO异常和JSON解析异常
            e.printStackTrace();
            // 可以选择记录日志、抛出运行时异常或返回错误码等
        }
        return result;
    }

    static JSONObject sendPostRequestTextBody(String PostUrl, String body, Map<String, String> additionalHeaders) {


//    String body = jsonObject.toString();
        HashMap<String, String> head = getHead(body, additionalHeaders);


        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody jsonBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), body);
        Request request = new Request.Builder()
                .url(PostUrl)
                .headers(DkcUtil.SetHeaders(head))
                .post(jsonBody)
                .build();

        JSONObject result = null;
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            result = JSONUtil.parseObj(response.body().string());
        } catch (IOException | JSONException e) {
            // 处理IO异常和JSON解析异常
            e.printStackTrace();
            // 可以选择记录日志、抛出运行时异常或返回错误码等
        }
        return result;
    }

    public static HashMap<String, String> getHead(String body, Map<String, String> additionalHeaders) {

        String token = "";

        //给sign传token  不然获取不到sign
        if (additionalHeaders.containsKey("authorization-token")) {
            token = additionalHeaders.get("authorization-token");
        }
        // sign
        System.out.println(body);
        String str = API_APP_KEY + body + token + API_APP_CODE;
        System.out.println(str);
        // 设置默认请求头
        HashMap<String, String> head = new HashMap<>();
        head.put("authorization-appkey", API_APP_KEY);
        head.put("app-version", "v1.9.8"); // 默认版本，可以根据需要修改或作为参数传入
        head.put("app-platform", "android");
        head.put("app-deviceid", "bb09f1ae6da97819"); // 默认设备ID，可以根据需要修改或作为参数传入
        head.put("authorization-sign", DkcUtil.getSHA256Hash(str));
        //head.put("authorization-sign", "31f8f5c5ad230b2ff7967ad50739bbed636788ec81a94d490a0e59741883dcd9");
      //  head.put("content-length", "22");
        // 合并额外的请求头
        if (additionalHeaders != null) {
            head.putAll(additionalHeaders);
        }
        return head;
    }
}

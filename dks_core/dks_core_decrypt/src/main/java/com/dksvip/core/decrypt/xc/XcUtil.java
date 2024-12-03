package com.dksvip.core.decrypt.xc;

import cn.hutool.core.lang.Validator;
import okhttp3.Headers;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * 喜茶微信小程序下单sign值生成
 */
public class XcUtil {
    public static void main(String[] args) {
        String t = "000001";
        String n = (Math.random() + "0000000000").substring(2,10);
        String o = String.valueOf(Math.floor((new Date().getTime()) / 1e3));
        String a = "cf5ed55e0046c240b57ee215c8bfe13c";
        String e = "heyteago-01F3C784D66955D9";
        String key = "000001heyteago-01F3C784D66955D9";
        String sign = Hmac_MD5((t+n+o+a+e),key);
        System.out.println(sign);
    }
    static Headers SetHeaders(Map<String, String> headersParams) {
        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();
        if (Validator.isNotEmpty(headersParams)) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        headers = headersbuilder.build();

        return headers;

    }


    public static String Hmac_MD5(String res,String key){
        try {
            Mac hmacMD5 = Mac.getInstance("HmacMD5");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),"HmacMD5");
            hmacMD5.init(secretKeySpec);
            byte[] hmacMD5bytes = hmacMD5.doFinal(res.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hmacMD5bytes.length);
            for (byte aByte : hmacMD5bytes) {
                String hex = Integer.toHexString(0xff & aByte);
                if (hex.length() == 1) hexString.append("0");
                hexString.append(hex);
            }
            return hexString.toString().toLowerCase();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("加密失败："+e);
            e.printStackTrace();
        }
        return "";
    }
}

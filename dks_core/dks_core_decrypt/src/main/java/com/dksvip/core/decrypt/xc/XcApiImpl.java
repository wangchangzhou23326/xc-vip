package com.dksvip.core.decrypt.xc;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcan
 * @date 2024/11/29 11:58
 */
@Service
@Slf4j
public class XcApiImpl implements XcApi {

    @Autowired
    private XcOkHttpClient client;

    @Override
    public JSONObject getMenuCategories(Integer isTakeaway, Integer shopId) {
        String PostUrl = "https://go.heytea.com/api/service-menu/grayapi/shop/categories?isTakeaway=" + isTakeaway + "&shopId=" + shopId;
        Map<String, String> additionalHeaders = new HashMap<>();
//        additionalHeaders.put("Accept-Charset", "UTF-8");
//        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/index");
//        additionalHeaders.put("accept-language", "zh-CN");
//        additionalHeaders.put("current-page", "pages/index/index");
//        additionalHeaders.put("client-version", "308.0.0");
//        additionalHeaders.put("x-release-type", "ONLINE");
//        additionalHeaders.put("version", "5.1.37");
//        additionalHeaders.put("gmt-zone", "+08:00");
//        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
//        additionalHeaders.put("authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMTgyMzcsIm5iZiI6MTczMzExODIzNywiZXhwIjoxNzMzMjA0NjM3LCJpc3MiOiJoZXl0ZWEifQ.qD_jdX0-xvM54hq-ShmzheS7qqGLZiaUign1L-kE_nk");
//        additionalHeaders.put("x-region-id", "10");
//        additionalHeaders.put("x-client", "alipay");
//        additionalHeaders.put("client", "3");
//        additionalHeaders.put("content-type", "application/json");
//        additionalHeaders.put("region", "1");
//        additionalHeaders.put("x-version", "5.1.37");
//        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYZpPsIGWXJy+Kz5iCBw+3LwIYlvJrjV0se7oGNwW705T0gc9vm/Qjx3+ziRlQCQHYNXWAQAdBrVJ+kYqYLPb3jE=");
//        additionalHeaders.put("Connection", "Keep-Alive");
//        additionalHeaders.put("Host", "go.heytea.com");
        return client.sendGetRequest(PostUrl, additionalHeaders);
    }

    @Override
    public JSONObject getProductInfo(Integer isTakeaway, Integer menuType, Integer productIds, Integer shopId) {
        String url = "https://go.heytea.com/api/service-menu/vip/grayapi/v4/shop/product-info?isTakeaway=" + isTakeaway + "&menuType=" + menuType + "&productIds=" + productIds + "&shopId=" + shopId;
        Map<String, String> additionalHeaders = new HashMap<>();
//        additionalHeaders.put("Accept-Charset","UTF-8");
//        additionalHeaders.put("referer","https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/index");
//        additionalHeaders.put("accept-language","zh-CN");
//        additionalHeaders.put("current-page","pages/index/index");
//        additionalHeaders.put("client-version","308.0.0");
//        additionalHeaders.put("x-release-type","ONLINE");
//        additionalHeaders.put("version","5.1.37");
//        additionalHeaders.put("gmt-zone","+08:00");
//        additionalHeaders.put("accept","application/prs.heytea.v1+json");
//        authorization 授权 --- 有时效性
//        additionalHeaders.put("authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMjA2MDQsIm5iZiI6MTczMzEyMDYwNCwiZXhwIjoxNzMzMjA3MDA0LCJpc3MiOiJoZXl0ZWEifQ.otmT9-knCc0-pJcZe0nSQqP7gJaJfI1fH_cgLAiwfus");
//        additionalHeaders.put("x-region-id","10");
//        additionalHeaders.put("x-client","alipay");
//        additionalHeaders.put("client","3");
//        additionalHeaders.put("content-type","application/json");
//        additionalHeaders.put("region","1");
//        additionalHeaders.put("x-version","5.1.37");
//        additionalHeaders.put("alipayMiniMark","nmD/fQQ1B9xRv7INmjacYZpPsIGWXJy+Kz5iCBw+3LwIYlvJrjV0se7oGNwW705T0gc9vm/Qjx3+ziRlQCQHYNXWAQAdBrVJ+kYqYLPb3jE=");
//        additionalHeaders.put("Connection","Keep-Alive");
//        additionalHeaders.put("Host","go.heytea.com");
        return client.sendGetRequest(url, additionalHeaders);
    }

    @Override
    public JSONObject getUserCoupon() {
        String postUrl = "https://vip.heytea.com/api/service-coupon/couponLibrary/unused-page/v2";

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("page", 1).set("size", 10);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/coupon/index?__appxPageId=5");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMjgzMTksIm5iZiI6MTczMzEyODMxOSwiZXhwIjoxNzMzMjE0NzE5LCJpc3MiOiJoZXl0ZWEifQ.KrkA0btGVmmhLdgLgqZbwawslExRBLsr9MkslE3e58Y");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYf1wp7435u2DEDQclWcA17VoZDFlHXw40t34Xt/IhaTT0krz2LTGQMJvRyx8Ap8/f1/tf5JAqUsmEmuWNhviazc=");
        additionalHeaders.put("Content-Length", "20");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendPostRequest(postUrl, jsonObject, additionalHeaders);
    }

    @Override
    public JSONObject getUserBalance() {
        String postUrl = "https://go.heytea.com/api/service-account/wallet/account/detail";
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxOTE1MTEsIm5iZiI6MTczMzE5MTUxMSwiZXhwIjoxNzMzMjc3OTExLCJpc3MiOiJoZXl0ZWEifQ.noTpHPa3m_x8Ac95VpTciymdnpmY-D6kf2gmM3ZaYXw ");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendPostRequest(postUrl, new JSONObject(), additionalHeaders);
    }

    @Override
    public JSONObject getExchangeCouponList(Integer memberType, Integer page) {
        String getUrl = "https://vip.heytea.com/api/service-member/mall_product/productLst?member_type=" + memberType + "&page=" + page;

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxODkwNDUsIm5iZiI6MTczMzE4OTA0NSwiZXhwIjoxNzMzMjc1NDQ1LCJpc3MiOiJoZXl0ZWEifQ.L0jN9N3ohHK4KqrdsGDQSnsS8kqq-ZHIfTv3Dh1uzTk");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");

        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    @Override
    public JSONObject getUsableScore() {
        String getUrl = "https://vip.heytea.com/api/service-member/vip/member/usable-score";
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxOTA0NjAsIm5iZiI6MTczMzE5MDQ2MCwiZXhwIjoxNzMzMjc2ODYwLCJpc3MiOiJoZXl0ZWEifQ.2LyskMq6Z67d4uHiI_Sfy86IFeWKHeM5ikX0C1Dmav8");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    @Override
    public JSONObject getCurrentOrder() {
        String getUrl = "https://go.heytea.com/api/service-oms-order/grayapi/order/current";
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxOTc1NTAsIm5iZiI6MTczMzE5NzU1MCwiZXhwIjoxNzMzMjgzOTUwLCJpc3MiOiJoZXl0ZWEifQ.3WwvAbm-ucoPL8OEFSCs7cWs23Qe8XehOGGj6rW4icI");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    @Override
    public JSONObject exchangeCouponsByCode(String ticketCode) {
        String posUrl = "https://vip.heytea.com/api/service-member/vip/coupon-library/coupon/exchange";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ticketCode);
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("x-release-type", "ONLINE");
        additionalHeaders.put("version", "5.1.37");
        additionalHeaders.put("gmt-zone", "+08:00");
        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxOTc1NTAsIm5iZiI6MTczMzE5NzU1MCwiZXhwIjoxNzMzMjgzOTUwLCJpc3MiOiJoZXl0ZWEifQ.3WwvAbm-ucoPL8OEFSCs7cWs23Qe8XehOGGj6rW4icI");
        additionalHeaders.put("x-region-id", "10");
        additionalHeaders.put("x-client", "alipay");
        additionalHeaders.put("client", "3");
        additionalHeaders.put("content-type", "application/json");
        additionalHeaders.put("region", "1");
        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendPostRequest(posUrl, jsonObject, additionalHeaders);
    }

    public static void main(String[] args) {
        String PostUrl = "https://go.heytea.com/api/service-smc/grayapi/user/closest/shop";
        JSONObject jsonObject = new JSONObject("{\"location\":\"114.277981,30.709883\",\"id\":4935,\"type\":0}");

//        String urlString = "https://your-api-endpoint.com"; // 替换成你的 URL

        /*// 请求体内容，例如发送 JSON 数据
        String postData = "{\"key1\":\"value1\", \"key2\":\"value2\"}";  // 发送的 JSON 数据

        try {
            // 创建 URL 对象
            URL url = new URL(urlString);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为 POST
            connection.setRequestMethod("POST");

            // 启用输入和输出流
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // 设置请求头 (Content-Type 和其他头)
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // 获取输出流，并写入请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes(StandardCharsets.UTF_8); // 将数据转换为字节数组
                os.write(input, 0, input.length); // 发送数据
            }

            // 获取响应输入流
            InputStream inputStream = connection.getInputStream();

            // 读取响应体，使用正确的编码
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line;
            StringBuilder responseBody = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            System.out.println("Response body: " + responseBody.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();
        headersbuilder.add("Accept-Charset", "UTF-8")
                .add("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411261800.22/index.html#pages/index/index")
                .add("accept-language", "zh-CN")
                .add("current-page", "pages/index/index")
                .add("client-version", "306.0.0")
                .add("x-release-type", "ONLINE")
                .add("version", "5.1.37")
                .add("gmt-zone", "+08:00")
                .add("accept", "application/prs.heytea.v1+json")
                .add("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzI4NjU3MzEsIm5iZiI6MTczMjg2NTczMSwiZXhwIjoxNzMyOTUyMTMxLCJpc3MiOiJoZXl0ZWEifQ.g4IWQADfvRgr0TbwfyoPn7qADQbFSrdGOUorLuOf_Ug")
                .add("x-region-id", "10")
                .add("client", "3")
                .add("x-client", "alipay")
                .add("content-type", "application/json")
                .add("region", "1")
                .add("x-version", "5.1.37")
                //.add("Accept-Encoding","gzip")
                .add("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYei4s2TvOVFZafMFUaAB/rLHfowXB0l7bBTb4X1EA/1bCGXOZNTj4jrLdHAqvb9UepSEw4VZmivpSvXGMwgfjyA=")
                .add("Content-Length", "54")
                .add("Host", "go.heytea.com");

        headers = headersbuilder.build();

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONUtil.toJsonStr(jsonObject));
        Request request = new Request.Builder()
                .url(PostUrl)
                .headers(headers)
                .post(jsonBody)
                .build();

        JSONObject result = null;
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            System.out.println(responseBody);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}

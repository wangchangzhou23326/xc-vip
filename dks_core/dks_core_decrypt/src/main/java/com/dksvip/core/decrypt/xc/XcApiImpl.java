package com.dksvip.core.decrypt.xc;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    private String API_URL = "https://go.heytea.com/api/";

    @Override
    public JSONObject getMenuCategories(String isTakeaway, String shopId) {
        String PostUrl = API_URL + "service-menu/grayapi/shop/categories?isTakeaway=" + isTakeaway + "&shopId=" + shopId;
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
    public JSONObject getProductInfo(String isTakeaway, String menuType, String productIds, String shopId) {
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
    public JSONObject getExchangeCouponList(String memberType, String page) {
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
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2NTc5MjI4IiwidXNlcl9tYWluX2lkIjo3NjgzOTExMiwiY2hhbm5lbCI6IloiLCJzb3VyY2UiOiJhcGkiLCJpc19ndWVzdCI6ZmFsc2UsImxhYmVsIjoiY2xpZW50OmFsaXBheSIsImlhdCI6MTczMzMwMDg3MywibmJmIjoxNzMzMzAwODczLCJleHAiOjE3MzMzODcyNzMsImlzcyI6ImhleXRlYSJ9.HoJm3zqHXwBKLDWgxyqFCos7MnHedOd1asW40-JWWlc");
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

    /**
     * 获取当前城市门店
     *
     * @param location 经纬度坐标
     * @param id       门店Id
     * @param type     门店类型
     * @return
     */
    @Override
    public JSONObject getLocationShop(String location, int id, int type) {
        String PostUrl = API_URL + "service-smc/grayapi/user/closest/shop";


        // 请求体
//        JSONObject jsonObject = new JSONObject("{\"location\":\"114.278457,30.711057\",\"id\":3161,\"type\":0}");
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("location", location);
        jsonObject.set("id", id);
        jsonObject.set("type", type);
        // 额外的请求头（如果有）
        Map<String, String> additionalHeaders = new HashMap<>();
////        additionalHeaders.put("Content-Type", "application/json");
//        additionalHeaders.put("x-region-id", "10");
//        additionalHeaders.put("x-client", "alipay");
//        additionalHeaders.put("client","3");
//        additionalHeaders.put("content-type","application/json");
//        additionalHeaders.put("region","1");
////        additionalHeaders.put("Accept-Encoding","gzip");
//        additionalHeaders.put("alipayMiniMark","nmD/fQQ1B9xRv7INmjacYei4s2TvOVFZafMFUaAB/rLHfowXB0l7bBTb4X1EA/1bCGXOZNTj4jrLdHAqvb9UepSEw4VZmivpSvXGMwgfjyA=");
//        additionalHeaders.put("Content-Length","54");
//        additionalHeaders.put("Connection","Keep-Alive");
//        additionalHeaders.put("Host","go.heytea.com");
//        Headers.Builder headersbuilder = new Headers.Builder();
//        additionalHeaders.put("Accept-Charset","UTF-8");
//        additionalHeaders.put("referer","https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/index");
//        additionalHeaders.put("accept-language","zh-CN");
//        additionalHeaders.put("current-page","pages/index/index");
//        additionalHeaders.put("client-version","308.0.0");
//        additionalHeaders.put("x-release-type","ONLINE");
//        additionalHeaders.put("version","5.1.37");
//        additionalHeaders.put("gmt-zone","+08:00");
//        additionalHeaders.put("accept","application/prs.heytea.v1+json");
//    additionalHeaders.put("authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMTgyMzcsIm5iZiI6MTczMzExODIzNywiZXhwIjoxNzMzMjA0NjM3LCJpc3MiOiJoZXl0ZWEifQ.qD_jdX0-xvM54hq-ShmzheS7qqGLZiaUign1L-kE_nk");
//        additionalHeaders.put("x-region-id","10");
//        additionalHeaders.put("client","3");
//        additionalHeaders.put("x-client","alipay");
//        additionalHeaders.put("content-type","application/json");
//        additionalHeaders.put("region","1");
//        additionalHeaders.put("x-version","5.1.37");
        //.add("Accept-Encoding","gzip")
//        additionalHeaders.put("alipayMiniMark","nmD/fQQ1B9xRv7INmjacYT0Inv3rN14QVErhyK3GCbO52FKu4B7+o8QyjW2SW8kqkIo0I8Xx2e/41iAqgdt/io5yr1pdAQy04PTq2Jq85l8=");
//        additionalHeaders.put("Content-Length","54");
//        additionalHeaders.put("Host","go.heytea.com");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 获取所有城市
     *
     * @return
     */
    @Override
    public JSONObject getAllCity() {
        String PostUrl = API_URL + "service-location/grayapi/global-area";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", "");

        HashMap<String, String> additionalHeaders = new HashMap<>();
        //additionalHeaders.put("referer", " https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/store/choose_area_v2/index?__appxPageId=4&cityCode=156420100\n");
        //additionalHeaders.put("current-page", "pages/index/store/choose_area_v2/index");
        //additionalHeaders.put("client-version", "308.0.0");
        //additionalHeaders.put("authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMjA2MDQsIm5iZiI6MTczMzEyMDYwNCwiZXhwIjoxNzMzMjA3MDA0LCJpc3MiOiJoZXl0ZWEifQ.otmT9-knCc0-pJcZe0nSQqP7gJaJfI1fH_cgLAiwfus");
        //additionalHeaders.put("alipayMiniMark","nmD/fQQ1B9xRv7INmjacYae2k3FXdqVLO5jmQeC0XbvssStAHlaTQyEMIxYvMb7vNj7PQBLFL4z33HRWUFtziSmAOIO2jyf32Wb1D78oG34=");
        //additionalHeaders.put("Content-Length","14");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 获取当前城市的所有门店
     *
     * @param countryCode  国家代码
     * @param cityCode     城市代码
     * @param loadShopIds  第一次请求传空值，如果想获取更多地址，就需要将第一次获取到的id，存入改数组，如果第三次获取，就需要将前两次的的id全部存入数组当中。（相当于城市过滤器）
     * @param topShopId    首选商店id
     * @param strategy     策略
     * @param userLocation 用户位置
     * @return
     */
    @Override
    public JSONObject getCurrentCity(String countryCode, String cityCode, List<Object> loadShopIds, Integer topShopId, String strategy, String userLocation) {
        String PostUrl = API_URL + "service-smc/grayapi/city/shop-page";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("country_code", countryCode);
        jsonObject.put("city_code", cityCode);
        //转换为JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Object item : loadShopIds) {
            jsonArray.put(item);
        }
        jsonObject.put("loadShopIds", loadShopIds);
        jsonObject.put("topShopId", topShopId);
        jsonObject.put("strategy", strategy);
        jsonObject.put("user_location", userLocation);

        HashMap<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/store/choose_area_v2/index?__appxPageId=5&cityCode=156410500");
        additionalHeaders.put("client-version", "308.0.0");
        // additionalHeaders.put("authorization"," Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxMjQ1MzQsIm5iZiI6MTczMzEyNDUzNCwiZXhwIjoxNzMzMjEwOTM0LCJpc3MiOiJoZXl0ZWEifQ.wlPWpxYFCFJBrt_Uw8s6CjzGjCMrJjkNEheRJKlThbc");
        additionalHeaders.put("Content-Length", "132");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 获取附近门店
     *
     * @param distance      距离
     * @param shiftLocation 偏移位置
     * @param userLocation  用户位置
     * @return
     */
    @Override
    public JSONObject getNearShop(String distance, String shiftLocation, String userLocation) {
        String getUrl = String.format("https://go.heytea.com/api/service-delivery/grayapi/location-shop-list?distance=%s&shift_location=%s&user_location=%s", distance, shiftLocation, userLocation);
        Map<String, String> additionalHeaders = new HashMap<>();
//        additionalHeaders.put("Accept-Charset", "UTF-8");
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
//        additionalHeaders.put("accept-language", "zh-CN");
        additionalHeaders.put("current-page", "pages/coupon/index");
//        additionalHeaders.put("client-version", "308.0.0");
//        additionalHeaders.put("x-release-type", "ONLINE");
//        additionalHeaders.put("version", "5.1.37");
//        additionalHeaders.put("gmt-zone", "+08:00");
//        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
//        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMxOTA0NjAsIm5iZiI6MTczMzE5MDQ2MCwiZXhwIjoxNzMzMjc2ODYwLCJpc3MiOiJoZXl0ZWEifQ.2LyskMq6Z67d4uHiI_Sfy86IFeWKHeM5ikX0C1Dmav8");
//        additionalHeaders.put("x-region-id", "10");
//        additionalHeaders.put("x-client", "alipay");
//        additionalHeaders.put("client", "3");
//        additionalHeaders.put("content-type", "application/json");
//        additionalHeaders.put("region", "1");
//        additionalHeaders.put("x-version", "5.1.37");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
//        additionalHeaders.put("Connection", "Keep-Alive");
//        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    /**
     * 兑换喜茶卡
     *
     * @param cardNo   喜茶卡号
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject getExchangeCard(String cardNo, String password) {
        String PostUrl = API_URL + "service-member/vip/prepaid-card/exchange";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cardNo", cardNo);
        jsonObject.put("password", password);
        HashMap<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/member/exchange_code/exchange_area/index?__appxPageId=1&from=myIndex&type=giftCard");
        additionalHeaders.put("current-page", "pages/member/exchange_code/exchange_area/index");
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMyMDQwODUsIm5iZiI6MTczMzIwNDA4NSwiZXhwIjoxNzMzMjkwNDg1LCJpc3MiOiJoZXl0ZWEifQ.hSWlcZHeHuNqTv3auCSN5pWCdH5Yy4-0vxRIsRdAaGQ");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYVrg83SyVGsQyR1FQENEfg73mPRaPgiZ1pzcXz//I8/UtHIh73gQ25pq/SZBrnN6XF1LHEwL3jkx+beE+lE/Riw=");
        additionalHeaders.put("Content-Length", "34");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(4) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * @param param shopId           商店id
     * @param param isTakeaway       是否选择打包
     * @param param pmsData          用户信息
     * @param param benefitNos       权益信息
     * @param param isStudentMember  是否学生会员
     * @param param isChoosedBag     是否选择打包
     * @param param optionForm       1:堂食 2:外卖
     *              editType         1:新增 2:修改 3:删除
     *              product          商品信息
     *              ridedistance     外卖距离
     *              couponItems      优惠券信息
     * @return
     */
    @Override
    public JSONObject getCart(JSONObject param) {
        String PostUrl = API_URL + "service-cart/vip/grayapi/cart/person3/edit";
        JSONObject body = new JSONObject();
//        body.putOnce("shopId", param.getStr("shopId"));
//        body.putOnce("isTakeaway", param.getBool("isTakeaway"));
//        body.putOnce("pmsData", param.getJSONObject("pmsData"));
//        body.putOnce("benefitNos", param.getJSONArray("benefitNos"));
//        body.putOnce("isStudentMember", param.getBool("isStudentMember"));
//        body.putOnce("isChoosedBag", param.getBool("isChoosedBag"));
//        body.putOnce("optionFrom", param.getInt("optionFrom"));
//        body.putOnce("editType", param.getInt("editType"));
//        body.putOnce("product", param.getJSONObject("product"));
//        body.putOnce("rideDistance", param.getStr("rideDistance"));
//        body.putOnce("couponItems", param.getJSONArray("couponItems"));
// 提取公共参数
        body.putOnce("shopId", param.getStr("shopId"));
        body.putOnce("isTakeaway", param.getBool("isTakeaway"));
        body.putOnce("pmsData", param.getJSONObject("pmsData"));
        body.putOnce("benefitNos", param.getJSONArray("benefitNos"));
        body.putOnce("isStudentMember", param.getBool("isStudentMember"));
        body.putOnce("isChoosedBag", param.getBool("isChoosedBag"));
//        body.putOnce("rideDistance", param.getStr("rideDistance"));
//        body.putOnce("couponItems", param.getJSONArray("couponItems"));

        // 根据action字段决定是添加还是删除
        String action = param.getStr("action");
        if ("add".equalsIgnoreCase(action)) {
            // 添加购物车项
            body.putOnce("optionFrom", param.getInt("optionFrom"));
            body.putOnce("editType", 1); // 假设1代表添加操作
            body.putOnce("product", param.getJSONObject("product"));
        } else if ("delete".equalsIgnoreCase(action)) {
            // 删除购物车项
            // 注意：这里的cartItem结构可能与添加时不同，需要根据实际情况调整
            body.putOnce("optionFrom", 0); // 假设0代表删除操作（或这个字段对删除操作不重要）
//            body.putOnce("editType", -1); // 假设-1代表删除操作
            // 注意：这里的cartItem应该是一个能够唯一标识要删除的购物车项的结构
            // 由于你的示例中cartItem结构与添加时不同，这里我们假设需要提供一个uniqueNo
            JSONObject cartItem = new JSONObject();
            cartItem.put("uniqueNo", param.getJSONObject("cartItem").getStr("uniqueNo"));
            // 如果后端需要其他信息（如productId和quantity），也需要添加进来
            // 但在这个例子中，我们假设uniqueNo已经足够
            body.putOnce("cartItem", cartItem);
            body.putOnce("editType", -1); // 假设-1代表删除操作
        } else {
            // 未知操作，返回错误
            return new JSONObject().put("error", "Unknown action");
        }
        body.putOnce("rideDistance", param.getStr("rideDistance"));
        body.putOnce("couponItems", param.getJSONArray("couponItems"));

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/combo_v2/index?__appxPageId=3&productId=7366&shopId=3161");
        additionalHeaders.put("current-page", "pages/index/combo_v2/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2NTc5MjI4IiwidXNlcl9tYWluX2lkIjo3NjgzOTExMiwiY2hhbm5lbCI6IloiLCJzb3VyY2UiOiJhcGkiLCJpc19ndWVzdCI6ZmFsc2UsImxhYmVsIjoiY2xpZW50OmFsaXBheSIsImlhdCI6MTczMzI5NjEyMywibmJmIjoxNzMzMjk2MTIzLCJleHAiOjE3MzMzODI1MjMsImlzcyI6ImhleXRlYSJ9.QrylA88EYwkbgpn0j_NUO4X7yD343GEHNNSvuHmcV7A");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYfXN1BP1oQHE4JIwTyTxuFS0abxgOw/EaKFMa3xMULFmfmo5pCu8cl2vFi50fuuaIX5FfLt0/CWl6QtWUySD52g=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(6) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        return client.sendPostRequest(PostUrl, body, additionalHeaders);

    }

    @Override
    public JSONObject clearCart(JSONObject jsonObject) {
        String PostUrl = API_URL + "service-cart/vip/grayapi/cart/person3/clear";
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/index/combo_v2/index?__appxPageId=3&productId=7366&shopId=3161");
        additionalHeaders.put("current-page", "pages/index/combo_v2/index");
        additionalHeaders.put("client-version", "308.0.0");
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2NTc5MjI4IiwidXNlcl9tYWluX2lkIjo3NjgzOTExMiwiY2hhbm5lbCI6IloiLCJzb3VyY2UiOiJhcGkiLCJpc19ndWVzdCI6ZmFsc2UsImxhYmVsIjoiY2xpZW50OmFsaXBheSIsImlhdCI6MTczMzI5NjEyMywibmJmIjoxNzMzMjk2MTIzLCJleHAiOjE3MzMzODI1MjMsImlzcyI6ImhleXRlYSJ9.QrylA88EYwkbgpn0j_NUO4X7yD343GEHNNSvuHmcV7A");
        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYfXN1BP1oQHE4JIwTyTxuFS0abxgOw/EaKFMa3xMULFmfmo5pCu8cl2vFi50fuuaIX5FfLt0/CWl6QtWUySD52g=");
        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(6) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
        additionalHeaders.put("Connection", "Keep-Alive");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    @Override
    public JSONObject getUserInfo() {
        String getUrl = API_URL + "service-member/vip/user/center/info";
        Map<String, String> additionalHeaders = new HashMap<>();
//        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
//        additionalHeaders.put("accept-language", "zh-CN");
//        additionalHeaders.put("current-page", "pages/coupon/index");
//        additionalHeaders.put("client-version", "308.0.0");
//        additionalHeaders.put("x-release-type", "ONLINE");
//        additionalHeaders.put("version", "5.1.37");
//        additionalHeaders.put("gmt-zone", "+08:00");
//        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMzMDI1MTEsIm5iZiI6MTczMzMwMjUxMSwiZXhwIjoxNzMzMzg4OTExLCJpc3MiOiJoZXl0ZWEifQ.OlgsVEUOKLbwBl9tt2zSALP46N3KzCLwWnzrg3y5kbI");
//        additionalHeaders.put("x-region-id", "10");
//        additionalHeaders.put("x-client", "alipay");
//        additionalHeaders.put("client", "3");
//        additionalHeaders.put("content-type", "application/json");
//        additionalHeaders.put("region", "1");
//        additionalHeaders.put("x-version", "5.1.37");
//        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
//        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
//        additionalHeaders.put("Connection", "Keep-Alive");
//        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    @Override
    public JSONObject getUserAddressList() {
//        https://go.heytea.com/api/service-delivery/vip/address/shop/addresses-location
        String getUrl = API_URL + "service-delivery/vip/address/shop/addresses-location";
        Map<String, String> additionalHeaders = new HashMap<>();
//        additionalHeaders.put("Accept-Charset", "UTF-8");
//        additionalHeaders.put("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411292308.3/index.html#pages/mall/index?__appxPageId=1");
//        additionalHeaders.put("accept-language", "zh-CN");
//        additionalHeaders.put("current-page", "pages/coupon/index");
//        additionalHeaders.put("client-version", "308.0.0");
//        additionalHeaders.put("x-release-type", "ONLINE");
//        additionalHeaders.put("version", "5.1.37");
//        additionalHeaders.put("gmt-zone", "+08:00");
//        additionalHeaders.put("accept", "application/prs.heytea.v1+json");
        // authorization 授权 --- 有时效性
        additionalHeaders.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzMyODE5NzgsIm5iZiI6MTczMzI4MTk3OCwiZXhwIjoxNzMzMzY4Mzc4LCJpc3MiOiJoZXl0ZWEifQ.8bpO4HAudB7cgFIykdv27xMJqDoCVH3yBnwZIVXchNo");
//        additionalHeaders.put("x-region-id", "10");
//        additionalHeaders.put("x-client", "alipay");
//        additionalHeaders.put("client", "3");
//        additionalHeaders.put("content-type", "application/json");
//        additionalHeaders.put("region", "1");
//        additionalHeaders.put("x-version", "5.1.37");
//        additionalHeaders.put("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYSrU0g7huXypzI+sLvVHSMOUALndZntLCP1sZrvt46ghOFcRgO+fcxwr/+assxnUre19dEHTlOizZbesNCu4XTE=");
//        additionalHeaders.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11; zh-CN; MI 8 Lite Build/RKQ1.200826.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 UWS/3.22.2.66 Mobile Safari/537.36 UCBS/3.22.2.66_230817192043 ChannelId(8) NebulaSDK/1.8.100112 Nebula AlipayDefined(nt:WIFI,ws:393|0|2.75) AliApp(AP/10.5.26.8000) AlipayClient/10.5.26.8000 Language/zh-Hans useStatusBar/true isConcaveScreen/true Region/CNAriver/1.0.0 DTN/2.0");
//        additionalHeaders.put("Connection", "Keep-Alive");
//        additionalHeaders.put("Host", "vip.heytea.com");
        return client.sendGetRequest(getUrl, additionalHeaders);
    }

    /**
     * 登录
     *
     * @param authCode 授权码
     * @return
     */
    @Override
    public String getLogin(String authCode) {
        String postUrl = API_URL + "service-login/openapi/vip/user/login_v1";
        JSONObject body = new JSONObject();
        body.set("channel", "Z");
        body.set("client", "alipay");
        body.set("loginType", "MINI");
        body.set("brand", "1000001");
        body.set("authCode", "e102c502675a4113946a26a353d6TX59");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body.toString());
        Request request = new Request.Builder()
                .url(postUrl)
                .post(jsonBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);

            }
            String responseBody = response.body().string();
            return "Bearer " + new JSONObject(responseBody).getJSONObject("data").get("token");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}


//    public static void main(String[] args) {
//        String PostUrl = "https://go.heytea.com/api/service-smc/grayapi/user/closest/shop";
//        JSONObject jsonObject = new JSONObject("{\"location\":\"114.277981,30.709883\",\"id\":4935,\"type\":0}");
//
////        String urlString = "https://your-api-endpoint.com"; // 替换成你的 URL
//
//        /*// 请求体内容，例如发送 JSON 数据
//        String postData = "{\"key1\":\"value1\", \"key2\":\"value2\"}";  // 发送的 JSON 数据
//
//        try {
//            // 创建 URL 对象
//            URL url = new URL(urlString);
//            // 打开连接
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            // 设置请求方法为 POST
//            connection.setRequestMethod("POST");
//
//            // 启用输入和输出流
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//
//            // 设置请求头 (Content-Type 和其他头)
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            // 获取输出流，并写入请求体
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = postData.getBytes(StandardCharsets.UTF_8); // 将数据转换为字节数组
//                os.write(input, 0, input.length); // 发送数据
//            }
//
//            // 获取响应输入流
//            InputStream inputStream = connection.getInputStream();
//
//            // 读取响应体，使用正确的编码
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//
//            String line;
//            StringBuilder responseBody = new StringBuilder();
//
//            while ((line = reader.readLine()) != null) {
//                responseBody.append(line);
//            }
//
//            System.out.println("Response body: " + responseBody.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//
//        Headers headers = null;
//        Headers.Builder headersbuilder = new Headers.Builder();
//        headersbuilder.add("Accept-Charset", "UTF-8")
//                .add("referer", "https://2019032763715272.hybrid.alipay-eco.com/2019032763715272/0.2.2411261800.22/index.html#pages/index/index")
//                .add("accept-language", "zh-CN")
//                .add("current-page", "pages/index/index")
//                .add("client-version", "306.0.0")
//                .add("x-release-type", "ONLINE")
//                .add("version", "5.1.37")
//                .add("gmt-zone", "+08:00")
//                .add("accept", "application/prs.heytea.v1+json")
//                .add("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDU2MjgzNzg5IiwidXNlcl9tYWluX2lkIjoxNTQyNTI1MzUsImNoYW5uZWwiOiJaIiwic291cmNlIjoiYXBpIiwiaXNfZ3Vlc3QiOmZhbHNlLCJsYWJlbCI6ImNsaWVudDphbGlwYXkiLCJpYXQiOjE3MzI4NjU3MzEsIm5iZiI6MTczMjg2NTczMSwiZXhwIjoxNzMyOTUyMTMxLCJpc3MiOiJoZXl0ZWEifQ.g4IWQADfvRgr0TbwfyoPn7qADQbFSrdGOUorLuOf_Ug")
//                .add("x-region-id", "10")
//                .add("client", "3")
//                .add("x-client", "alipay")
//                .add("content-type", "application/json")
//                .add("region", "1")
//                .add("x-version", "5.1.37")
//                //.add("Accept-Encoding","gzip")
//                .add("alipayMiniMark", "nmD/fQQ1B9xRv7INmjacYei4s2TvOVFZafMFUaAB/rLHfowXB0l7bBTb4X1EA/1bCGXOZNTj4jrLdHAqvb9UepSEw4VZmivpSvXGMwgfjyA=")
//                .add("Content-Length", "54")
//                .add("Host", "go.heytea.com");
//
//        headers = headersbuilder.build();
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONUtil.toJsonStr(jsonObject));
//        Request request = new Request.Builder()
//                .url(PostUrl)
//                .headers(headers)
//                .post(jsonBody)
//                .build();
//
//        JSONObject result = null;
//        Call call = okHttpClient.newCall(request);
//        try (Response response = call.execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//            String responseBody = response.body().string();
//            System.out.println(responseBody);
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }


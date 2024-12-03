package com.dksvip.core.decrypt.dks;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * @author zhangcan
 * @date 2024/11/13 15:40
 */
@Service
@Slf4j
public class DksAllApilmpl implements DksAllApi {

    private String API_URL = "https://app-api.dicos.com.cn/";
    private String Token ="b81371ddac8ab518c39e731d8222b722";

    @Autowired
    private DksOkHttpClient client;

    /**
     * 获取当前的城市
     *
     * @param latitude  精度 30.716811197791536
     * @param longitude 纬度 0
     * @return
     */
    @Override
    public JSONObject getCityInfo(double latitude, double longitude) {


        String PostUrl = API_URL + "api/v1/city/location";


        // 请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("latitude", latitude);
        jsonObject.set("longitude", longitude );
        // 额外的请求头（如果有）
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("app-deviceid", "1");
        additionalHeaders.put("app-version", "1.0");
//        additionalHeaders.put("accept","application/json, text/plain, */*");
//        additionalHeaders.put("x-request-id","bb09f1ae6da97819/1732502517674");
//        additionalHeaders.put("app-platform", "android");
//        additionalHeaders.put("authorization-token","9602dfd201835178861040b02ecb8b0b");
//        additionalHeaders.put("authorization-sign","7b91a9686a8752f894a5c90235bbb27a77af4a4ee2fae2320d796a039fdb48e0");
//        additionalHeaders.put("app-version", "v1.9.8"); // 默认版本，可以根据需要修改或作为参数传入
//        additionalHeaders.put("app-deviceid", "bb09f1ae6da97819"); // 默认设备ID，可以根据需要修改或作为参数传入
//        additionalHeaders.put("sensors-req","%7B%22nameValuePairs%22%3A%7B%22%24is_first_day%22%3Afalse%2C%22%24latest_utm_campaign%22%3A%22%22%2C%22%24latest_utm_content%22%3A%22%22%2C%22%24latest_utm_medium%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22%22%2C%22%24latest_utm_term%22%3A%22%22%2C%22%24refer_page_title%22%3A%22%E9%A6%96%E9%A1%B5%22%2C%22%24current_page_title%22%3A%22%E6%88%91%E7%9A%84%22%7D%7D");
//        additionalHeaders.put("app-ri-token","mGPHH1732501663Th0U0DsbQp8");
//        additionalHeaders.put("content-type","application/json; charset=UTF-8")
//        additionalHeaders.put("content-length", "61");
//        additionalHeaders.put("accept-encoding""gzip");
//        additionalHeaders.put("user-agent","okhttp/4.9.1");        //head.put("authorization-sign", "31f8f5c5ad230b2ff7967ad50739bbed636788ec81a94d490a0e59741883dcd9");
//        //  head.put("content-length", "22");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 获取全部城市
     * @return
     */
    @Override
    public JSONObject getAllCities() {
        String PostUrl = API_URL + "api/v1/city/list";
        // 请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("app-ri-token", "sGPHX1731546189OzfqEUbNC4a");

        // 额外的请求头（如果有）
        Map<String, String> additionalHeaders = new HashMap<>(); // 不需要额外的请求头，除非有其他需求
//        additionalHeaders.put("authorization-appkey","6ef4ac053cc98979a34a12af164f045c")
//        additionalHeaders.put("x-request-id","bb09f1ae6da97819/1732503627132");
//        additionalHeaders.put("app-version", "v1.9.8"); // 默认版本，可以根据需要修改或作为参数传
//        additionalHeaders.put("app-platform", "android"
//        additionalHeaders.put("app-deviceid", "bb09f1ae6da97819");
//        additionalHeaders.put("app-ri-token","mGPHH1732501663Th0U0DsbQp8");
//        additionalHeaders.put("authorization-sign","2a9db5205e500be8df9c94af1c7aef3f05231638a61c38ee51c727fa28d0b577");
//        additionalHeaders.put("authorization-token","9602dfd201835178861040b02ecb8b0b");
//        additionalHeaders.put("content-type","text/plain")
//        additionalHeaders.put("content-length", "2");
//        additionalHeaders.put("accept-encoding""gzip");
//        additionalHeaders.put("user-agent","okhttp/4.9.3");
        // 但由于我们已经在默认请求头中设置了app-deviceid和app-version，所以这里不需要再设置

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 获取城市门店
     *
     * @param longitude 精度  114.304569
     * @param latitude 纬度   30.593354
     * @return
     */
    @Override
    public JSONObject getStoreByLocation(double longitude, double latitude) {

        String PostUrl = API_URL + "api/v1/store/list";
        // 请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("longitude", longitude);
        jsonObject.set("latitude", latitude);
        jsonObject.set("radius", 30000);

        // 额外的请求头（如果有）
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("app-ri-token", "sGPHG17316330801eqQQRgVcH9");
//        additionalHeaders.put("authorization-appkey","6ef4ac053cc98979a34a12af164f045c")
//        additionalHeaders.put("authorization-sign","2a9db5205e500be8df9c94af1c7aef3f05231638a61c38ee51c727fa28d0b577");
//        additionalHeaders.put("cookies","XSRF-TOKEN");
//        additionalHeaders.put("x-request-id","bb09f1ae6da97819/1732503617653");
//        additionalHeaders.put("app-version", "v1.9.8"); // 默认版本，可以根据需要修改或作为参数传
//        additionalHeaders.put("app-platform", "android"
//        additionalHeaders.put("app-deviceid", "bb09f1ae6da97819");
//        additionalHeaders.put("app-ri-token","mGPHH1732501663Th0U0DsbQp8");
//        additionalHeaders.put("authorization-token","9602dfd201835178861040b02ecb8b0b");
//        additionalHeaders.put("content-type","text/plain")
//        additionalHeaders.put("content-length", "104");
//        additionalHeaders.put("accept-encoding""gzip");
//        additionalHeaders.put("user-agent","okhttp/4.9.3");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 商品菜单
     *
     * @param storeCode 商店标识 103709
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getProductMenus(String storeCode,String eatStyle) {
        String PostUrl = API_URL + "api/v1/store/store_menus_v2";

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("store_code", storeCode);
        jsonObject.set("eat_style", eatStyle);
        jsonObject.set("match_promotion", 24);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token", Token);
        JSONObject jsonObject1 = client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
        return jsonObject1;
    }

    /**
     * 获取附近城市
     * @param cityCode 城市标识 220800
     * @return
     */
    @Override
    public JSONObject getCityByLocation(String cityCode) {

        String PostUrl = API_URL + "api/v1/store/list_by_city";
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.set("city_code", "420100");*/
        String str = "{\"city_code\":\"420100\"}";
        JSONObject jsonObject = JSONUtil.parseObj(str);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token", Token);
//        additionalHeaders.put("authorization-appkey","6ef4ac053cc98979a34a12af164f045c")
//        additionalHeaders.put("authorization-sign","2a9db5205e500be8df9c94af1c7aef3f05231638a61c38ee51c727fa28d0b577");
//        additionalHeaders.put("x-request-id","bb09f1ae6da97819/1732503617653");
//        additionalHeaders.put("app-version", "v1.9.8"); // 默认版本，可以根据需要修改或作为参数传
//        additionalHeaders.put("app-platform", "android"
//        additionalHeaders.put("app-deviceid", "bb09f1ae6da97819");
//        additionalHeaders.put("app-ri-token","mGPHH1732501663Th0U0DsbQp8");
//        additionalHeaders.put("authorization-token","9602dfd201835178861040b02ecb8b0b");
//        additionalHeaders.put("content-type","text/plain")
//        additionalHeaders.put("content-length", "104");
//        additionalHeaders.put("accept-encoding""gzip");
//        additionalHeaders.put("user-agent","okhttp/4.9.3");
        return client.sendPostRequestTextBody(PostUrl, JSONUtil.toJsonStr(jsonObject), additionalHeaders);
    }

    /**
     * 促销
     * @param storeCode 商店标识 100175
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getStorePromotion(String storeCode,String eatStyle) {
        String PostUrl = API_URL + "api/v1/store/promotion_v2";
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("store_code", storeCode);
        jsonObject.set("eat_style", eatStyle);
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("app-ri-token", "sGPHG17316330801eqQQRgVcH9");
        additionalHeaders.put("authorization-token", Token);

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 根据系统id获取商品详细菜单
     * @param systemId 系统id    2_0_1397
     * @param storeCode 商店标识  100175
     * @param eatStyle  饮食风格  ts
     * @param classExtId
     * @return classExtId
     */
    @Override
    public JSONObject getProductMenuDetail(String storeCode,String eatStyle,String classExtId,String systemId,String childId) {
        String PostUrl = API_URL + "api/v1/store/menu_detail";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("store_code",storeCode );
        jsonObject.put("eat_style", eatStyle);
        jsonObject.put("class_ext_id", classExtId);
        jsonObject.put("system_id", systemId);
        jsonObject.put("match_promotion", 24);
        jsonObject.put("child_class_ext_id",childId);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token", Token);

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 根据token apptoken获取用户信息
     //     * @param appToken   kGPHJ1731650038pUR94kEHVe3
     * @return
     */
    @Override
    public JSONObject getUserInformation(String token) {
        String PostUrl = API_URL + "api/v1/user/center";
        JSONObject jsonObject = new JSONObject();

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token", token);

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }




    /**
     *  通过code获取进入餐厅信息
     * @param storeCode  商店标识 104342
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getStoreInfo(String storeCode,String eatStyle) {
        String PostUrl = API_URL + "api/v1/store/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("store_code",storeCode );
//        jsonObject.set("longitude",114.27642611026049 );
//        jsonObject.set("latitude",30.71721113384515 );
        jsonObject.set("eat_style",eatStyle );

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("app-ri-token", "kGPHJ1731650038pUR94kEHVe3");
//        additionalHeaders.put("authorization-token", "8b29d5582f28ece0ec9c6fde431d3027");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 券状态列表
     * @return
     */
    @Override
    public JSONObject getCouponStatusList() {
        String PostUrl = API_URL + "api/v1/user_coupon/tab_list";
        JSONObject jsonObject= new JSONObject();
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("app-ri-token", "kGPHJ1731650038pUR94kEHVe3");
        additionalHeaders.put("authorization-token", Token);

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 券类型
     * @param type  类型数量 3
     * @return
     */
    @Override
    public JSONObject getCouponType(int type) {
        String PostUrl = API_URL + "api/v1/user_coupon/tab_detail";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("type",type);
        jsonObject.set("page",1);
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 更新购物车信息
     * @param orderId           订单编号   1731893601245053976
     * @param classExtId        类拓展编号    2285
     * @param systemId          系统Id       2_1_21004185
     * @param productCategroy   产品类别      1
     * @param quantity          数量         1
     * @param condimentList     调料列表
     * @return
     */
    @Override
    public JSONObject getCartUpdate(String orderId, String classExtId, String systemId, int productCategroy,int quantity, List<Object> condimentList) {

        String PostUrl = API_URL + "api/v1/cart/update";

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("order_id",orderId);
        jsonObject.set("class_ext_id",classExtId);
        jsonObject.set("system_id",systemId);
        jsonObject.set("product_category",productCategroy);
        jsonObject.set("quantity",quantity);
        // 将condimentList转换为JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Object item : condimentList) {
            jsonArray.put(item);
        }
        jsonObject.put("condiment_list", jsonArray);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);


    }

    /**
     * 通过订单编号获取订单信息
     * @param orderSn 订单编号 20241115150248000001
     * @return
     */
    @Override
    public JSONObject getOrderinfoById(String orderSn) {
        String PostUrl = API_URL + "api/v1/order/detail";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("order_sn",orderSn);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 订单列表
     * @param eatStyle 饮食风格 wm
     * @param page     页码
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public JSONObject getOrderList( String eatStyle,int page, int pageSize) {
        String PostUrl=API_URL+"api/v1/order/list";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("eat_style",eatStyle);
        jsonObject.set("page",page);
        jsonObject.set("page_size", pageSize);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 获取支付信息
     * @param orderSn  订单编号  20241115151835000001
     * @param payType  支付方式  wechat
     * @return
     */
    @Override
    public JSONObject getPayInformation(String orderSn, String payType) {
        String PostUrl =API_URL + "api/v1/order/pay";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("order_sn",orderSn);
        jsonObject.set("pay_type",payType);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 开通尊享卡
     * @return
     */
    @Override
    public JSONObject  getPremiumCard() {
        String PostUrl =API_URL + "api/v1/benefit/goods/list_v2";
        JSONObject jsonObject= new JSONObject();


        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        additionalHeaders.put("app-ri-token","kGPHJ1731650038pUR94kEHVe3");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 收货地址信息
     * @return
     */
    @Override
    public JSONObject getShippingAddress() {
        String PostUrl =API_URL + "api/v1/user/address_list";
        JSONObject jsonObject= new JSONObject();
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
//        additionalHeaders.put("app-ri-token","kGPHJ1731650038pUR94kEHVe3");

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 优惠券信息
     * @param storeCode 商店标识        104342
     * @param matchPromotion 促销标识   26
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getUserCouponInfo( String storeCode,int matchPromotion,String eatStyle,String token) {
        String PostUrl =API_URL + "api/v1/cart/coupon_list_v2";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("store_code",storeCode);
        jsonObject.set("eat_style",eatStyle);
        jsonObject.set("place_id","menu_page");
        jsonObject.set("page",1);
        jsonObject.set("page_size",10);
        jsonObject.set("match_promotion",matchPromotion);
        jsonObject.set("tab_type","usable");


        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",token);
        additionalHeaders.put("app-ri-token","vGPHg1731658708yUkjaiBYyp5");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 购物车
     * @param storeCode  商店标识  100175
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getShoppingCart(String storeCode,String eatStyle) {
        String PostUrl = API_URL + "api/v1/cart/query";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("store_code",storeCode);
        jsonObject.set("eat_style",eatStyle);
        jsonObject.set("match_promotion",24);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 清空购物车
     * @param storeCode 商店标识  80002506
     * @param orderId   订单编号  1731638389970149551
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @Override
    public JSONObject getCartClear(String storeCode, String orderId,String eatStyle) {
        String PostUrl = API_URL + "api/v1/cart/clear";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("store_code",storeCode);
        jsonObject.set("order_id",orderId);
        jsonObject.set("eat_style",eatStyle);


        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);

        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 领券中心
     * @param cityCode      城市标识        420100
     * @param categoryUuid   类别对应的标识  4192b64ee8154fb0946402be9f1bf250
     * @return
     */
    @Override
    public JSONObject getCouponCenter(String cityCode, String categoryUuid) {
        String PostUrl = API_URL +"api/v1/coupon/list";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("city_code",cityCode);
        jsonObject.set("category_uuid",categoryUuid);
        jsonObject.set("size",10);
        jsonObject.set("page",1);

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
//        additionalHeaders.put("app-ri-token","jGPHG1731900736D4IRL79OtK2");
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 获取积分
     * @return
     */
    @Override
    public JSONObject getPoint() {
        String PostUrl = API_URL + "api/v1/user/point";
        JSONObject jsonObject= new JSONObject();
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 券信息
     * @param storeCode  商店标识
     * @param eatStyle   饮食风格
     * @param ticketCode 券标识
     * @param channel     渠道
     * @param quantity    数量
     * @return
     */
    @Override
    public JSONObject getCouponInformation(String storeCode, String eatStyle, String ticketCode,
                                 int channel, int quantity) {
        String PostUrl = API_URL + "api/v1/cart/coupon_v2";
        JSONObject jsonObject= new JSONObject();
        jsonObject.set("store_code",storeCode);
        jsonObject.set("eat_style",eatStyle);
        jsonObject.set("ticket_code",ticketCode);
        jsonObject.set("channel",channel);
        jsonObject.set("quantity",quantity);
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public JSONObject getLogout() {
        String PostUrl = API_URL + "api/v1/user/logout";
        JSONObject jsonObject= new JSONObject();

        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        additionalHeaders.put("app-ri-token","lGPHW1732088670DN7YLc6eGM8");


        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }
    /**
     * 短信验证码
     * @param mobile 手机号 13971116178
     * @return
     */
    @Override
    public JSONObject  getVerificationCode(String mobile) {
        String PostUrl = API_URL + "api/v1/login/send_sms_code";
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("mobile",mobile );

        Map<String, String> additionalHeaders = new HashMap<>();
        return DksOkHttpClient.sendPostRequest(PostUrl, jsonObject, additionalHeaders);

    }

    /**
     * 登录
     * @param mobile     手机号
     * @param ssmCode    验证码
     * @param uuid       用户id
     * @param storeCode  商店标识
     * @return
     */
    @Override
    public JSONObject getLogin(String mobile, String ssmCode, String uuid, String storeCode) {
        String PostUrl = API_URL + "api/v1/login/verify_sms_code";
//        JSONObject jsonObject= new JSONObject();
//        jsonObject.set("mobile",mobile);
//        jsonObject.set("ssm_code",ssmCode);
//        jsonObject.set("uuid",uuid);
//        jsonObject.set("store_code",storeCode);
        String body = "{\"mobile\": \""+mobile+"\",\"sms_code\": \""+ssmCode+"\",\"uuid\": \""+uuid+"\",\"store_code\": \"\"}";
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("content-length", "22");
//        additionalHeaders.put("app-ri-token",appToken);
//        long currentTimeMillis = System.currentTimeMillis();
//        String currentTimeStamp = String.valueOf(currentTimeMillis);
//        additionalHeaders.put("x-request-id","bb09f1ae6da97819/"+currentTimeStamp);
////      additionalHeaders.put("sensors-req","%7B%22refer_page_title%22%3A%22%E6%88%91%E7%9A%84%22%2C%22current_page_title%22%3A%22%E7%9F%AD%E4%BF%A1%E7%99%BB%E5%BD%95%E9%A1%B5%22%2C%22%24is_first_day%22%3A%22false%22%2C%22%24latest_utm_campaign%22%3A%22%22%2C%22%24latest_utm_content%22%3A%22%22%2C%22%24latest_utm_medium%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22%22%2C%22%24latest_utm_term%22%3A%22%22%7D");
////      additionalHeaders.put("accept-encoding","gzip");
//        additionalHeaders.put("user-agent","okhttp/4.9.3");
        return client.sendPostRequestTextBody(PostUrl, body, additionalHeaders);
    }

    /**
     * 取消订单
     * @param orderSn  订单号
     * @return
     */
    @Override
    public JSONObject getCancelOrder(String orderSn) {
        String PostUrl = API_URL + "api/v1/order/cancel";
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("order_sn",orderSn);
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("authorization-token",Token);
        return client.sendPostRequest(PostUrl, jsonObject, additionalHeaders);
    }



    public static void main(String[] args) {
        //
        DksOkHttpClient dksOkHttpClient = new DksOkHttpClient();
        String url = "https://app-api.dicos.com.cn/api/v1/store/store_menus_v2";
        String body = "{\n" +
                "  \"store_code\" : \"104495\",\n" +
                "  \"eat_style\" : \"ts\",\n" +
                "  \"match_promotion\" : 24\n" +
                "}";
        JSONObject jsonBody = JSONUtil.parseObj(body);
        String jsonStr = JSONUtil.toJsonStr(jsonBody);
        HashMap<String, String> addHead = new HashMap<>();

        //获取sign

        String token = "9602dfd201835178861040b02ecb8b0b";
        //String str ="709a6c5d81ceddf6ccadc0f4b153d813d9681ca0"+ body + token +"6ef4ac053cc98979a34a12af164f045c" ;
        String str ="6ef4ac053cc98979a34a12af164f045c"+jsonStr+token+"709a6c5d81ceddf6ccadc0f4b153d813d9681ca0" ;
        System.out.println(str);
        System.out.println("6ef4ac053cc98979a34a12af164f045c{\"store_code\":\"104495\",\"eat_style\":\"ts\",\"match_promotion\":24}9602dfd201835178861040b02ecb8b0b709a6c5d81ceddf6ccadc0f4b153d813d9681ca0");
        String sha256Hash = DkcUtil.getSHA256Hash(str);

        addHead.put("authorization-sign",sha256Hash);
        addHead.put("authorization-token",token);
        HashMap<String, String> head = dksOkHttpClient.getHead(body, addHead);
        JSONObject jsonObject = dksOkHttpClient.sendPostRequest(url, jsonBody, head);
        System.out.println(jsonObject);
    }

}
















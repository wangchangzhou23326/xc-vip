package com.dksvip.core.web.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dksvip.common.response.Result;
import com.dksvip.core.api.dks.DksApiClient;

import com.dksvip.core.web.service.DksApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 瑞幸接口  (不带业务)
 *
 * @Author:
 * @CreateTime:
 */
@Slf4j
@RequestMapping("/dksAllApi")
@RestController
public class DksApiController implements DksApiClient {

    @Autowired
    private DksApiService dksApiServicel;

    /**
     * 根据经纬度获取城市信息
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return 包含城市信息的 JSON 对象
     *
     *{
     *     "code": "200",
     *     "data": {
     *         "city_code": "420100",
     *         "city_name": "武汉",
     *         "city_pinyin": "WUHAN",
     *         "abbr": "WH",
     *         "is_gaode_code": false,
     *         "desc": ""
     *     },
     *     "msg": "success"
     * }
     *
     *
     */
    @GetMapping("/getCityInfo")
    public ResponseEntity<JSONObject> getCityInfo(@RequestParam double latitude, @RequestParam double longitude) {
        JSONObject cityInfo = dksApiServicel.getCityInfo(latitude, longitude);
        return ResponseEntity.ok(cityInfo);
    }
    /**
     * 获取全部城市列表
     *
     * @return 包含所有城市信息的 JSON 对象
     */
    @PostMapping("/getAllCities")
    public ResponseEntity<JSONObject> getAllCities() {
        JSONObject cityList = dksApiServicel.getAllCities();
        return ResponseEntity.ok(cityList);
    }

    /**
     * 获取附近城市
     *
     * @param cityCode 城市标识 220800
     * @return 包含附近城市信息的 JSON 对象
     */
    @GetMapping("/getCityByLocation")
    public ResponseEntity<JSONObject> getCityByLocation(@RequestParam String cityCode) {
        JSONObject cityInfo = dksApiServicel.getCityByLocation(cityCode);
        return ResponseEntity.ok(cityInfo);
    }

    /**
     * 获取城市门店
     *
     * @param longitude 精度  114.304569
     * @param latitude  纬度   30.593354
     * @return 包含城市门店信息的 JSON 对象
     */
    @GetMapping("/getStoreByLocation")
    public ResponseEntity<JSONObject> getStoreByLocation( @RequestParam double longitude,@RequestParam double latitude) {
        JSONObject cityInfo = dksApiServicel.getStoreByLocation(longitude, latitude);
        return ResponseEntity.ok(cityInfo);
    }
    /**
     * 商品菜单
     *
     * @param storeCode 商店标识 103709
     * @param eatStyle  饮食风格  ts
     * @return 包含商品菜单信息的 JSON 对象
     */
    @GetMapping("/getProductMenus")
    public ResponseEntity<JSONObject> getProductMenus(@RequestParam String storeCode,String eatStyle) {
        JSONObject storeInfo = dksApiServicel.getProductMenus(storeCode ,eatStyle);
        return ResponseEntity.ok(storeInfo);

    }
    /**
     *  通过code获取进入餐厅信息
     * @param storeCode  商店标识 105039
     * @param eatStyle   就餐方式  wm
     * @return 包含进入餐厅信息的 JSON 对象
     */
    @GetMapping("/getStoreInfo")
    public ResponseEntity<JSONObject> getStoreInfo(@RequestParam String storeCode,@RequestParam String eatStyle) {
        JSONObject storeInfo = dksApiServicel.getStoreInfo(storeCode, eatStyle);
        return ResponseEntity.ok(storeInfo);
    }

    /**
     * 促销
     * @param storeCode 商店标识 105039
     * @param eatStyle  就餐方式  ts
     * @return 包含促销信息的 JSON 对象
     */
    @GetMapping("/getStorePromotion")
    public ResponseEntity<JSONObject> getStorePromotion(@RequestParam String storeCode,@RequestParam String eatStyle) {
        JSONObject storeInfo = dksApiServicel.getStorePromotion(storeCode, eatStyle);
        return ResponseEntity.ok(storeInfo);
    }
    /* * 根据系统id获取商品详细菜单
     * storeCode 商店标识  105039
     * systemId 系统id    2_0_100124101402
     * classExtId  菜单id  1550
     * eatStyle  就餐风格   ts
     * @return 包含商品详细菜单信息的 JSON 对象
     *
     */
    /*public ResponseEntity<JSONObject> getStoreMenuDetail(
              @RequestBody String storeCode
             ,@RequestParam String systemId
             ,@RequestParam String eatStyle
             ,@RequestParam String classExtId)*/
    @PostMapping("/getProductMenuDetail")
    public ResponseEntity<JSONObject> getProductMenuDetail(
            @RequestBody JSONObject jsonObject){

        JSONObject storeInfo = dksApiServicel.getProductMenuDetail
                (jsonObject.getStr("storeCode"),jsonObject.getStr("eatStyle"),
                 jsonObject.getStr("classExtId"),jsonObject.getStr("systemId"),jsonObject.getStr("childClassExtId"));
        return ResponseEntity.ok(storeInfo);
    }

    /**
     * 更新购物车信息
     *  orderId           订单编号   1732601801251083736
     *  classExtId        菜单id     1550
     *  systemId          系统Id      2_0_100124030501
     *  productCategroy   产品类别      3
     *  quantity          数量         1
     * condimentList     调料列表
     * @return
     */
    @PostMapping("/getCartUpdate")
    public ResponseEntity<JSONObject> getCartUpdate(@RequestBody JSONObject jsonObject) {
        JSONObject cartInfo = dksApiServicel.getCartUpdate(jsonObject.getStr("order_id"),
                jsonObject.getStr("class_ext_id"),jsonObject.getStr("system_id"),
                jsonObject.getInt("product_category"),jsonObject.getInt("quantity"),
                jsonObject.getJSONArray("condiment_list"));
        return ResponseEntity.ok(cartInfo);
    }

    /**
     * 清空购物车
     * @param storeCode 商店标识  105039
     * @param orderId   订单编号  1732601801251083736
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @GetMapping("/getCartClear")
    public ResponseEntity<JSONObject> getCartClear(@RequestParam String storeCode,@RequestParam String orderId,@RequestParam String eatStyle) {
        JSONObject cartInfo = dksApiServicel.getCartClear(storeCode,orderId,eatStyle);
        return ResponseEntity.ok(cartInfo);
    }
    /**
     * 购物车
     * @param storeCode  商店标识  100175
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @GetMapping("/getShoppingCart")
    public ResponseEntity<JSONObject> getShoppingCart(@RequestParam String storeCode,@RequestParam String eatStyle) {
        JSONObject cartInfo = dksApiServicel.getShoppingCart(storeCode,eatStyle);
        return ResponseEntity.ok(cartInfo);
    }
    /**
     * 优惠券信息
     * @param storeCode 商店标识        104342
     * @param matchPromotion 促销标识   26
     * @param eatStyle  饮食风格  ts
     * @return
     */
    @GetMapping("/getUserCouponInfo")
    public ResponseEntity<JSONObject> getUserCouponInfo(@RequestParam String storeCode,@RequestParam int matchPromotion,@RequestParam String eatStyle,@RequestParam String token) {
        JSONObject cartInfo = dksApiServicel.getUserCouponInfo(storeCode,matchPromotion,eatStyle,token);
        return ResponseEntity.ok(cartInfo);
    }
    /**
     * 开通尊享卡
     * @return
     */
    @PostMapping("/getPremiumCard")
    public ResponseEntity<JSONObject> getPremiumCard() {
        JSONObject cartInfo = dksApiServicel.getPremiumCard();
        return ResponseEntity.ok(cartInfo);
    }


    /**
     * 订单列表
     * @param eatStyle  饮食风格 ts
     * @param page      饮食风格 1
     * @param pageSize  饮食风格 20
     * @return
     */
    @GetMapping("/getOrderList")
    public ResponseEntity<JSONObject> getOrderList(@RequestParam String eatStyle,@RequestParam int page,@RequestParam int pageSize) {
        JSONObject orderInfo = dksApiServicel.getOrderList(eatStyle,page,pageSize);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 获取支付信息
     * @param orderSn  订单编号  20241115151835000001
     * @param payType  支付方式  wechat
     * @return
     */
    @GetMapping("/getPayInformation")
    public ResponseEntity<JSONObject> getPayInformation(@RequestParam String orderSn,@RequestParam String payType) {
        JSONObject orderInfo = dksApiServicel.getPayInformation(orderSn,payType);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 通过订单编号获取订单信息
     * @param orderSn 订单编号 20241115150248000001
     * @return
     */
    @GetMapping("/getOrderinfoById")
    public ResponseEntity<JSONObject> getOrderinfoById(@RequestParam String orderSn) {
        JSONObject orderInfo = dksApiServicel.getOrderinfoById(orderSn);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 领券中心
     * @param cityCode      城市标识        420100
     * @param categoryUuid   类别对应的标识  4192b64ee8154fb0946402be9f1bf250
     * @return
     */
    @GetMapping("/getCouponCenter")
    public ResponseEntity<JSONObject> getCouponCenter(@RequestParam String cityCode,@RequestParam String categoryUuid) {
        JSONObject orderInfo = dksApiServicel.getCouponCenter(cityCode,categoryUuid);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 根据token apptoken获取用户信息

     * @return
     */
    @GetMapping("/getUserInformation")
    public ResponseEntity<JSONObject> getUserInformation(@RequestParam String token) {
        JSONObject orderInfo = dksApiServicel.getUserInformation(token);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 获取积分
     * @return
     */
    @GetMapping("/getPoint")
    public ResponseEntity<JSONObject> getPoint() {
        JSONObject orderInfo = dksApiServicel.getPoint();
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 收货地址信息
     * @return
     */
    @PostMapping("/getShippingAddress")
    public ResponseEntity<JSONObject> getShippingAddress() {
        JSONObject orderInfo = dksApiServicel.getShippingAddress();
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 券类型
     * @param type  类型数量 3
     * @return
     */
    @GetMapping("/getCouponType")
    public ResponseEntity<JSONObject> getCouponType(@RequestParam int type) {
        JSONObject orderInfo = dksApiServicel.getCouponType(type);
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 券状态列表
     * @return
     */
    @PostMapping("/getCouponStatusList")
    public ResponseEntity<JSONObject> getCouponStatusList() {
        JSONObject orderInfo = dksApiServicel.getCouponStatusList();
        return ResponseEntity.ok(orderInfo);
    }
    /**
     * 短信验证码
     * @param mobile 移动标识 13971116178
     * @return
     */
    @GetMapping("/getVerificationCode")
    public ResponseEntity<JSONObject> getVerificationCode(@RequestParam String mobile) {
        JSONObject orderInfo = dksApiServicel.getVerificationCode(mobile);
        return ResponseEntity.ok(orderInfo);
    }

    /**
     * 券信息
     *  storeCode  商店标识
     *  eatStyle   饮食风格
     *  ticketCode 券标识
     *  channel     渠道
     *  quantity    数量
     * @return
     */
    @PostMapping("/getCouponInformation")
    public ResponseEntity<JSONObject> getCouponInformation(@RequestBody JSONObject jsonObject) {
        JSONObject orderInfo = dksApiServicel.getCouponInformation(jsonObject.getStr("store_code"),
                jsonObject.getStr("eat_style"),
                jsonObject.getStr("ticket_code"),
                jsonObject.getInt("channel"),
                jsonObject.getInt("quantity"));
        return ResponseEntity.ok(orderInfo);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("getLogout")
    public ResponseEntity<JSONObject> getLogout() {
        JSONObject orderInfo = dksApiServicel.getLogout();
        return ResponseEntity.ok(orderInfo);

    }
    /**
     * 登录
     * @param mobile     手机号
     * @param ssmCode    验证码
     * @param uuid       用户id
     * @param storeCode  商店标识
     * @return
     */
    @GetMapping("/getLogin")
    public ResponseEntity<JSONObject> getLogin(@RequestParam String mobile,
                                                 @RequestParam String ssmCode,
                                                 @RequestParam String uuid,
                                                 @RequestParam String storeCode
    ) {
        JSONObject orderInfo = dksApiServicel.getLogin(mobile,ssmCode,uuid,storeCode);
        return ResponseEntity.ok(orderInfo);
    }

    /**
     * 获取sign
     * @param
     * @return
     */
    @PostMapping("/sign")
    public ResponseEntity<String> getSign(@RequestBody JSONObject jsonObject) {
        String sign = dksApiServicel.getSign( JSONUtil.toJsonStr(jsonObject.getJSONObject("body")),jsonObject.getStr("token"));
//         System.out.println("authorization-token"+jsonObject.getStr("authorization-token"));
//         System.out.println("body"+jsonObject.getStr("body"));
        return ResponseEntity.ok(sign);
    }
    /**
     * 取消订单
     * @param orderSn  订单号
     * @return
     */
    @GetMapping("/getCancelOrder")
    public ResponseEntity<JSONObject> getCancelOrder(@RequestParam String orderSn) {
        JSONObject orderInfo = dksApiServicel.getCancelOrder(orderSn);
        return ResponseEntity.ok(orderInfo);
    }

}

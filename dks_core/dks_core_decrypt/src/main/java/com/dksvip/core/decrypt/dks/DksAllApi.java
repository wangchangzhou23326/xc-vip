package com.dksvip.core.decrypt.dks;


import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * @author zhangcan
 * @date 2024/11/13 15:39
 */
public interface DksAllApi {

    /**
     * 获取当前的城市
     */
   JSONObject  getCityInfo(double latitude,double longitude);

    /**
     * 获取所有的城市
     */

    JSONObject getAllCities();

    /**
     * 获取城市门店
     */
    JSONObject getStoreByLocation(double longitude, double latitude);


    /**
     *  根据StoreCode获取商品菜单
     */
    JSONObject getProductMenus(String storeCode,String eatStyle);

    /**
     *获取附近城市
     */
    JSONObject getCityByLocation(String cityCode);

    /**
     * 促销
     */
    JSONObject getStorePromotion(String storeCode,String eatStyle);

    /**
     * 根据系统id获取商品详细菜单
     */
    JSONObject getProductMenuDetail(String storeCode,String eatStyle,String classExtId,String systemId,String childClassExtId);

    /**
     * 根据token apptoken获取用户信息
     */
    JSONObject getUserInformation(String token);

    /**
     * 发送短信验证码
     */
    JSONObject getVerificationCode(String mobile);

    /**
     * 通过code获取进入餐厅信息
     */
    JSONObject getStoreInfo(String storeCode,String eatStyle);

    /**
     * 券状态列表
     */
    JSONObject getCouponStatusList();

    /**
     * 券类型
     */
    JSONObject getCouponType(int  type);

    /**
     *更新购物车信息
     */
    JSONObject getCartUpdate(String orderId, String classId, String systemId, int productCategroy, int quantity, List<Object> condimentList);


    /**
     * 通过订单编号获取订单信息
     */
    JSONObject getOrderinfoById(String orderSn);

    /**
     * 订单列表
     */
    JSONObject getOrderList(String eatStyle,int page,int pageSize);

    /**
     * 获取支付信息
     */
    JSONObject getPayInformation(String orderSn,String payType);

    /**
     * 开通尊享卡
     */
    JSONObject getPremiumCard();

    /**
     * 收货地址信息
     */
    JSONObject getShippingAddress();

    /**
     * 优惠券信息
     */
    JSONObject getUserCouponInfo(String storeCode,int matchPromotion,String eatStyle,String token);

    /**
     * 购物车
     */
    JSONObject getShoppingCart(String storeCode,String eatStyle);

    /**
     * 清空购物车
     */
    JSONObject getCartClear(String storeCode,String orderId,String eatStyle);

    /**
     * 领劵中心
     */

    JSONObject getCouponCenter(String cityCode,String categoryUuid);

    /**
     * 获取积分
     */
    JSONObject getPoint();

    /**
     * 获取券信息
     */
    JSONObject getCouponInformation(String storeCode,String eatStyle,String ticketCode,int channel,int quantity);

    /**
     * 退出登录
     */
    JSONObject getLogout();

    /**
     * 登录
     */
    JSONObject getLogin(String mobile,String ssmCode,String uuid,String storeCode);

    /**
     * 取消菜单
     */
    JSONObject getCancelOrder(String orderSn);
}


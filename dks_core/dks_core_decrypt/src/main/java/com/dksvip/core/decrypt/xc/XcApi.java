package com.dksvip.core.decrypt.xc;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * @author zhangcan
 * @date 2024/11/29 11:58
 */

public interface XcApi {
    /**
     * 获取菜单分类及商品列表
     */
    JSONObject getMenuCategories(Integer isTakeaway, Integer shopId);

    /**
     * 获取商品详情
     */
    JSONObject getProductInfo(Integer isTakeaway, Integer menuType, Integer productIds, Integer shopId);

    /**
     * 获取用户优惠券
     */
    JSONObject getUserCoupon();

    /**
     * 获取用户余额
     */
    JSONObject getUserBalance();

    /**
     * 获取可用积分
     */
    JSONObject getUsableScore();

    /**
     * 获取(积分)兑换券列表
     */
    JSONObject getExchangeCouponList(Integer memberType, Integer page);

    /**
     * 获取订单
     */
    JSONObject getCurrentOrder();

    /**
     * 兑换券兑换
     */
    JSONObject exchangeCouponsByCode(String ticketCode);

    /**
     * 当前城市门店
     */
    JSONObject getLocationShop(String location, int id, int type);

    /**
     * 所有城市
     */
    JSONObject getAllCity();

    /**
     * 当前城市的所有门店
     */
    JSONObject getCurrentCity(String countryCode, String cityCode, List<Object> loadShopIds, int topShopId, Integer strategy, String userLocation);

    /**
     * 附近门店
     */
    JSONObject getNearShop(String distance, String shiftLocation, String userLocation);

    /**
     * 兑换喜茶卡
     */
    JSONObject getExchangeCard(String cardNo, String password);

}

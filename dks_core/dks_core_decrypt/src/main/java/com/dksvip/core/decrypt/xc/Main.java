package com.dksvip.core.decrypt.xc;

/**
 * @author zhangcan
 * @date 2024/11/29 12:07
 */
public class Main {
    public static void main(String[] args) {
        XcApi xcApi = new XcApiImpl();

//        JSONObject response = xcApi.getShop("114.278457,30.711057",3161,0);
//        System.out.println(response);

//        JSONObject shopCategories = xcApi.getMenuCategories(0, 3161);
//        System.out.println("shopCategories = " + shopCategories);
//
//        JSONObject productInfo = xcApi.getProductInfo(0, 0, 7136, 3161);
//        System.out.println("productInfo = " + productInfo);


//        System.out.println(xcApi.getUsableScore());
//        System.out.println(xcApi.getCurrentOrder());

//        System.out.println(xcApi.getUserBalance());

//        System.out.println(xcApi.getLogo());


        System.out.println(xcApi.exchangeCouponsByCode("1000010010"));

    }
}

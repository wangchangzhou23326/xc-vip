package com.dksvip.core.decrypt.xc;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

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
//        JSONObject productInfo = xcApi.getProductInfo("0", "0", "7019", "3161");
//        System.out.println("productInfo = " + productInfo);

//        System.out.println(xcApi.getComboInfo("7366", "3161"));


//        System.out.println(xcApi.getUsableScore());
//        System.out.println(xcApi.getCurrentOrder());

//        System.out.println(xcApi.getUserBalance());

//        System.out.println(xcApi.getLogo());


//        System.out.println(xcApi.exchangeCouponsByCode("1000010010"));
      //        JSONObject response = xcApi.getLocationShpo("114.278045,30.71089",3161,0);
//        System.out.println(response);

//        JSONObject response1 = xcApi.getAllCity();
//        System.out.println(response1);

//        JSONArray objects = new JSONArray();
//        objects.add(3161);
//        objects.add(4935);
//        objects.add(3478);
//        objects.add(3954);
//
//        JSONObject response2 = xcApi.getCurrentCity("156","156420100",new JSONArray(),3161,"0","114.279803,30.709717");
//        System.out.println(response2);
//       JSONObject  response3 = xcApi.getNearShop("10000","114.278279%2C30.710764","114.278279%2C30.710764");
//        System.out.println(response3);
//
//       JSONObject response4 = xcApi.getExchangeCard("TTT","TTT");
//        System.out.println(response4);

//        JSONObject jsonObject = JSONUtil.parseObj("{\n" +
//                "  \"shopId\": 3161,\n" +
//                "  \"isTakeaway\": false,\n" +
//                "  \"pmsData\": {\n" +
//                "    \"10041002\": {\n" +
//                "      \"clientScene\": 1,\n" +
//                "      \"isStudentMember\": false,\n" +
//                "      \"timestamp\": 1733296883070,\n" +
//                "      \"isMemberPlus\": false\n" +
//                "    },\n" +
//                "    \"10041003\": {\n" +
//                "      \"benefitNos\": \"\"\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"benefitNos\": [],\n" +
//                "  \"isStudentMember\": false,\n" +
//                "  \"isChoosedBag\": false,\n" +
//                "  \"optionFrom\": 0,\n" +
//                "  \"cartItem\": {\n" +
//                "    \"uniqueNo\": \"s:|30400360|19999005,19999011,19999137,19999435,19999508,19999964|||\",\n" +
//                "    \"productId\": 7214,\n" +
//                "    \"quantity\": 0\n" +
//                "  },\n" +
//                "  \"editType\": -1,\n" +
//                "  \"rideDistance\": null,\n" +
//                "  \"couponItems\": []\n" +
//                "}");
//        jsonObject.putOnce("action", "delete");
//        JSONObject response5 = xcApi.getCart(jsonObject);
//        System.out.println(response5);
//        System.out.println(xcApi.getUserAddressList());


//        System.out.println(xcApi.getUserInfo());
        System.out.println(xcApi.getLogin(null));
    }
}

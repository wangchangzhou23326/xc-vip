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

//        JSONObject shopCategories = xcApi.getMenuCategories("0", "3161");
//        System.out.println("shopCategories = " + shopCategories);
//
//        JSONObject productInfo = xcApi.getProductInfo("0", "0", "7135", "3161");
//        System.out.println("productInfo = " + productInfo);

//        System.out.println(xcApi.getComboInfo("7366", "3161"));


//        System.out.println(xcApi.getUsableScore());
//        System.out.println(xcApi.getCurrentOrder());

//        System.out.println(xcApi.getUserBalance());

//        System.out.println(xcApi.getLogo());

//        System.out.println(xcApi.getUserCoupon());

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

//        System.out.println(xcApi.deleteAddress("79298020"));

//        System.out.println(xcApi.getUserInfo());
//        System.out.println(xcApi.getLogin(null));

//        JSONObject entries = JSONUtil.parseObj("{\"shopId\":4935,\"isTakeaway\":false,\"pmsData\":{\"10041002\":{\"clientScene\":1,\"isStudentMember\":false,\"timestamp\":1733361942895,\"isMemberPlus\":false},\"10041003\":{\"benefitNos\":\"\"}},\"benefitNos\":[],\"isStudentMember\":false,\"isChoosedBag\":false,\"rideDistance\":null,\"couponItems\":[]}");
//        System.out.println(xcApi.getCartList(entries));
//        System.out.println(xcApi.getOrderInfoByNo("H1071374955536457729535"));

//        System.out.println(xcApi.searchAddress("名流人和天地润和园402",1,"武汉"));

//
//        JSONObject entries = JSONUtil.parseObj("{\"addressChannel\":1,\"name\":\"包\",\"phone\":\"sPIl4B1QPk0E39V9VV+2HQ==\",\"cryptoLevel\":2,\"sex\":\"male\",\"label\":\"家\",\"address\":\"名流烧烤(名流人和天地润和园店)\",\"complete_address\":\"湖北省武汉市黄陂区盘龙城开发区楚天大道特1号名流人和天地润和园\",\"description\":\"402 2 1601\",\"latitude\":\"30.711592\",\"longitude\":\"114.277286\",\"citycode\":\"027\",\"country\":\"中国\",\"province\":\"湖北省\",\"city\":\"武汉市\",\"ad_code\":\"420116\",\"district\":\"黄陂区\",\"maintained\":true}");
//        System.out.println(xcApi.addAddress(entries));

    }
}

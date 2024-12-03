package com.dksvip.common.constant;

import java.util.HashMap;
import java.util.Map;


public interface VipCodeStatus {



    Integer NOT_USED = 0;

    Integer IN_ORDER = 1;

    Integer ORDER_FINISHED = 2;

    Integer CLOSED  = -1;


    Integer EXPIRED = -2;

    Integer ERROR = -3;


    Map<Integer ,String> codeMap = new HashMap<Integer, String>(){{
        put(NOT_USED,  "未使用");
        put(IN_ORDER,  "正在下单中");
        put(ORDER_FINISHED, "已完成");
        put(CLOSED, "兑换码已关闭");
        put(EXPIRED, "兑换码已过期");
        put(ERROR, "下单失败");
    }};


    static String getName(int codeStatus){
        return codeMap.get(codeStatus);
    }


}

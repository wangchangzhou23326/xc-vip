package com.dksvip.common.constant;

import java.util.HashMap;
import java.util.Map;

public interface VipOrderStatus {

    int NOT_PAY = 0;
    int HAS_PAYED = 1;
    int WAIT_SERVER = 2;
    int FINISHED = 3;
    int CANCELED = -1;
    int ERROR = -2;
    int OVERTIME = -3;

    Map<Integer ,String> codeMap = new HashMap<Integer, String>(){{
        put(NOT_PAY,  "订单待支付");
        put(HAS_PAYED,  "订单支付中");
        put(WAIT_SERVER, "等待出餐");
        put(FINISHED, "订单已完成");
        put(CANCELED, "已取消");
        put(ERROR, "订单异常");
        put(OVERTIME, "订单超时");
    }};

    static String getName(int codeStatus){
        return codeMap.get(codeStatus);
    }
}

//package com.dksvip.core.web.task;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.dksvip.core.dao.order.service.DaoVipOrderService;
//import com.dksvip.core.dao.rx.service.DaoGenerateInterfaceRxService;
//import com.dksvip.core.web.redis.RedisUtil;
//import com.dksvip.core.web.service.RxOpenService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//
//@Slf4j
//@Component
//public class RxOrderStatusTask {
//
//    @Autowired
//    private DaoVipOrderService daoVipOrderService;
//
//    @Autowired
//    private RxOpenService rxOpenService;
//
//    @Autowired
//    private DaoGenerateInterfaceRxService daoGenerateInterfaceRxService;
//
//    /**
//     *  put(NOT_PAY,  "订单待支付");
//     *         put(HAS_PAYED,  "订单支付中");
//     *         put(WAIT_SERVER, "等待出餐");
//     *         put(FINISHED, "订单已完成");
//     *         put(CANCELED, "已取消");
//     *         put(ERROR, "订单异常");
//     *         put(OVERTIME, "订单超时");
//     */
//    /**
//     * 每5s 与服务器同步更新 订单支付状态
//     */
//    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
//    public void updateOrder() {
//        //从redis中获取订单号，调用第三方订单查询接口
//        RedisUtil redisUtil = RedisUtil.getInstance();
//        Object batchCountObj = redisUtil.get("rx:order_id_list:batchCount");
//        int batchCount = 20;
//        if (batchCountObj == null) {
//            redisUtil.set("rx:order_id_list:batchCount", batchCount);
//        } else {
//            batchCount = Integer.parseInt(batchCountObj.toString());
//        }
//
//        List<JSONObject> orderIdList = new ArrayList<>();
//        for (int i = 0; i < batchCount; i++) {
//            Object obj = redisUtil.popItemFromList("rx:order_id_list");
//            if (obj != null) {
//                log.info("从redis中获取订单 ----------:{}",obj);
//                orderIdList.add(JSONUtil.parseObj(obj));
//            }
//        }
//        for (JSONObject jsonObject : orderIdList) {
//            //同步订单状态
//            rxOpenService.syncOrderStatus(jsonObject, redisUtil);
//        }
//    }
//}

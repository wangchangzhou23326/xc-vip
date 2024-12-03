//package com.dksvip.core.web.task;
//
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
//public class RxOrderCallbackTask {
//
//    @Autowired
//    private RxOpenService rxOpenService;
//
//    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
//    public void orderCallbackTask() {
//        //从redis中获取订单号
//        RedisUtil redisUtil = RedisUtil.getInstance();
//        Object batchCountObj = redisUtil.get("rx:order_id_list_callback:batchCount");
//        int batchCount = 20;
//        if (batchCountObj == null) {
//            redisUtil.set("rx:order_id_list_callback:batchCount", batchCount);
//        } else {
//            batchCount = Integer.parseInt(batchCountObj.toString());
//        }
//
//        List<String> orderIdList = new ArrayList<>();
//        for (int i = 0; i < batchCount; i++) {
//            Object obj = redisUtil.popItemFromList("rx:order_id_list_callback");
//            if (obj != null) {
//                log.info("从redis中获取回调订单 ----------:{}",obj);
//                orderIdList.add(obj.toString());
//            }
//        }
//        for (String groupOrderId : orderIdList) {
//            //回调
//            rxOpenService.orderCallback(groupOrderId, redisUtil);
//        }
//    }
//
//
//}

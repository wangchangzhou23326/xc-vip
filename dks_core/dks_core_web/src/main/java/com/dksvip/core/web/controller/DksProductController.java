package com.dksvip.core.web.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dksvip.core.api.dks.DksApiClient;
import com.dksvip.core.dao.product.DaoProductPackageDks;
import com.dksvip.core.web.redis.RedisUtil;
import com.dksvip.core.web.service.DksApiService;
import com.dksvip.core.web.service.DksProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 瑞幸接口  (不带业务)
 *
 * @Author:
 * @CreateTime:
 */
@Slf4j
@RequestMapping("/dksProduct")
@RestController
public class DksProductController implements DksApiClient {

    @Autowired
    private DksProductService dksProductService;

    /**
     * 同步商品
     */
    @PostMapping("/syncProduct")
    @Async
    public void getRxCoupon(
            @RequestParam("storeCode") String storeCode,
            @RequestParam("userId") Long userId
    ) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        //每次执行先清空计数器、只查看最近一次同步情况
        redisUtil.del("dks:syncProductPackageKeySuccess:" + userId);
        redisUtil.del("dks:syncProductPackageKeyError:" + userId);
        redisUtil.set("dks:syncProductPackageKeyBeginTime:" + userId, DateUtil.formatDateTime(DateUtil.date()));
        // 预计条数
        redisUtil.set("dks:syncProductPackageKeyCount:" + userId, 0);

        // 记录开始时间
        long start = System.currentTimeMillis();
        try {
            //redis避免重复处理
            //只有存在，就表示还没有处理完，在调用的时候去判断一下
            redisUtil.set("dks:syncProductPackageKey:" + userId, "1", 1, TimeUnit.HOURS);

            // 获取门店经典菜单列表
            List<DaoProductPackageDks> list = dksProductService.getClassicMenuProductList(storeCode, userId, 0);

            if (list.size() > 0) {
                //如果uidList不为空，那按照uid去查询
                int index = 0;
                for (DaoProductPackageDks productPackageDks : list) {
                    log.info("list===================" + index++);
                    dksProductService.saveRxProductPackage(productPackageDks, storeCode, userId);
                }
                // 预计条数
                redisUtil.set("dks:syncProductPackageKeyCount:" + userId, list.size());
            }

            // 记录结束时间
            long end = System.currentTimeMillis();
            // 计算耗时，单位为毫秒
            long executeTime = end - start;
            // 打印耗时
            log.info("所有异步任务已完成!执行耗时：{} 毫秒", executeTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.del("dks:syncProductPackageKey:" + userId);
            redisUtil.set("dks:syncProductPackageKeyEndTime:" + userId, DateUtil.formatDateTime(DateUtil.date()));
            // 记录结束时间
            long end = System.currentTimeMillis();
            // 计算耗时，单位为毫秒
            long executeTime = end - start;
            // 打印耗时
            log.info("getUserInfoKey所有异步任务已完成!执行耗时：{} 毫秒", executeTime);
        }
    }
}

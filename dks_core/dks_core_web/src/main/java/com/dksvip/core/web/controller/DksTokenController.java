package com.dksvip.core.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.dksvip.core.dao.token.DaoTokenDks;
import com.dksvip.core.web.redis.RedisUtil;
import com.dksvip.core.web.service.DksTokenService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("dksApi")
@RequiredArgsConstructor
public class DksTokenController {

    private final DksTokenService DksTokenService;

    /**
     * 用户信息同步
     * @param uidList 用户token列表
     * @param typeId 分类id
     * @param userId 操作人用户id
     * @return
     */
    @PostMapping("/accountSyncInfo")
    public void accountSyncInfo(@RequestParam("uidList") String[] uidList, @RequestParam("typeId") String typeId, @RequestParam("userId") Integer userId) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        //每次执行先清空计数器、只查看最近一次同步情况
        redisUtil.del("dks:accountSyncByUidKeySuccess:" + userId);
        redisUtil.del("dks:accountSyncByUidKeyError:" + userId);
        redisUtil.set("dks:accountSyncBeginTime:" + userId, DateUtil.formatDateTime(DateUtil.date()));

        // 预计条数
        redisUtil.set("dks:accountSyncByUidKeyCount:" + userId, 0);

        // 记录开始时间
        long start = System.currentTimeMillis();

        try {
            //redis避免重复处理
            //只有存在，就表示还没有处理完，在调用的时候去判断一下
            redisUtil.set("dks:accountSyncByUidKey:" + userId, "1", 1, TimeUnit.HOURS);

            int pageSize = 100; //每一页数量
            List<Future<String>> futures = new ArrayList<>();
            //如果uidList不为空 && 分类id为空，那按照uid去查询
            if (uidList.length > 0 && Validator.isEmpty(typeId)) {
                // 预计条数
                redisUtil.set("dks:accountSyncByUidKeyCount:" + userId, uidList.length);
                //如果uidList不为空，那按照uid去查询
                futures.add(DksTokenService.accountSyncInfo(uidList, userId));
            }

            //如果uidList不为空 && typeId也不为空、则按照传过来的uid进行处理
            if (uidList.length > 0 && Validator.isNotEmpty(typeId)) {
                // 预计条数
                redisUtil.set("dks:accountSyncByUidKeyCount:" + userId, uidList.length);
                //如果uidList不为空，那按照uid去查询
                futures.add(DksTokenService.accountSyncInfo(uidList, userId));
            }

            //如果uidList为空 && typeId不为空，那按照typeId去查询
            if (Validator.isNotEmpty(typeId) && uidList.length < 1) {
                //总行数
                int count = DksTokenService.getAccountCount(typeId, userId);
                // 预计条数
                redisUtil.set("dks:accountSyncByUidKeyCount:" + userId, count);
                //总页数
                int pageNum = (int) Math.ceil(count * 1.0 / pageSize);
                for (int i = 1; i <= pageNum; i++) {
                    PageInfo<DaoTokenDks> pageInfo = DksTokenService.getAccountList(i, pageSize, typeId, userId);
                    String[] uids = pageInfo.getList().stream().map(DaoTokenDks::getUid).toArray(String[]::new);
                    futures.add(DksTokenService.accountSyncInfo(uids, userId));
                }
            }

            //如果uidList为空 && typeId为空，那查询所有
            if (Validator.isEmpty(typeId) && uidList.length == 0) {
                //总行数
                int count = DksTokenService.getAccountCount(userId);
                // 预计条数
                redisUtil.set("dks:getGiftCardByUidKeyCount:" + userId, count);
                //总页数
                int pageNum = (int) Math.ceil(count * 1.0 / pageSize);
                for (int i = 1; i <= pageNum; i++) {
                    PageInfo<DaoTokenDks> pageInfo = DksTokenService.getAccountList(i, pageSize, userId);
                    String[] uids = pageInfo.getList().stream().map(DaoTokenDks::getUid).toArray(String[]::new);
                    futures.add(DksTokenService.accountSyncInfo(uids, userId));
                }
            }

            // 等待所有任务完成
            for (Future<String> future : futures) {
                try {
                    future.get(); // 这里会阻塞，直到每个Future任务完成
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt(); // 重新设置中断状态
                }
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
            redisUtil.del("dks:accountSyncByUidKey:" + userId);
            redisUtil.set("dks:accountSyncByUidKeyEndTime:" + userId, DateUtil.formatDateTime(DateUtil.date()));
            // 记录结束时间
            long end = System.currentTimeMillis();
            // 计算耗时，单位为毫秒
            long executeTime = end - start;
            // 打印耗时
            log.info("getUserInfoKey所有异步任务已完成!执行耗时：{} 毫秒", executeTime);
        }
    }


}

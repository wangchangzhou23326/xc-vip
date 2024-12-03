package com.dksvip.core.web.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dksvip.common.exception.RxLoginCallException;
import com.dksvip.core.dao.coupon.mapper.DaoCouponDksMapper;
import com.dksvip.core.dao.coupon.service.DaoCouponDksService;
import com.dksvip.core.dao.token.DaoTokenDks;
import com.dksvip.core.dao.token.mapper.DaoTokenDksMapper;
import com.dksvip.core.dao.token.service.DaoTokenDksService;
import com.dksvip.core.web.controller.DksApiController;
import com.dksvip.core.web.redis.RedisUtil;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DksTokenService {

    private final DaoTokenDksService daoTokenDksService;
    private final DksApiController dksApiController;
    private final DaoTokenDksMapper daoTokenDksMapper;


    public Future<String> accountSyncInfo(String[] uidList, Integer userId) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        log.info("执行异步任务accountSyncInfoByUidKey：{}", Thread.currentThread().getName());

        for (String uid : uidList) {
            //int state = streamGiftCard(uid, userId, 0);
            int state = getAccountByTokenKey(uid, userId, 0);
            if (state > 0) {
                //失败一次加1
                redisUtil.incr("dks:accountSyncByUidKeyError:" + userId, 1);
            } else {
                //成功一次加1
                redisUtil.incr("dks:accountSyncByUidKeySuccess:" + userId, 1);
            }
        }
        return new AsyncResult<>("Task accountSyncInfo completed");
    }

    private int getAccountByTokenKey(String uid, Integer userId, int count) {
        if (count >= 3) {
            return 1;
        }
        try {
            //登录 解析json信息
            JSONObject res = getAccountInfoByToken(uid);

            //根据解析结果 插入数据库
            DaoTokenDks daoTokenDks = daoTokenDksMapper.selectOne(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUid, uid));
            daoTokenDks = ObjUtil.isEmpty(daoTokenDks) ? new DaoTokenDks() : daoTokenDks;
            daoTokenDks.setUid(uid);
            daoTokenDks.setUserId(userId);
            daoTokenDks.setUpdateTime(DateUtil.date());
            //todo 根据具体逻辑 设置使用状态 分类id 所属分类名称
            daoTokenDks.setStatus(0);
            daoTokenDks.setBalance(new BigDecimal(0));
            daoTokenDks.setMoneyBag(new BigDecimal(0));
            daoTokenDks.setLoginStatus(res != null ? 1 : 0);
            daoTokenDks.setPhoneNumber(res != null ? res.getJSONObject("data").getStr("mobile") : null);
            if (daoTokenDks.getId()  == null) {
                daoTokenDks.setId(IdUtil.getSnowflakeNextId());
                daoTokenDks.setCreateTime(DateUtil.date());
                daoTokenDksMapper.insert(daoTokenDks);
            }else {
                daoTokenDksMapper.updateById(daoTokenDks);
            }

            /*JSONObject res = getGiftCard(uid, null);
            streamGiftCardBusiness(uid, res, userId);*/
            return 0;
        } catch (RxLoginCallException e) {
            changeLoginState(uid);
            log.info("uid {} 未登录，将uid表数改为未登录！", uid);
        } catch (Exception e) {
            e.printStackTrace();
            count = count + 1;
            log.info("同步礼品卡出现未知异常 {}，重试，第 {} 次：", e.getMessage(), count);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            getAccountByTokenKey(uid, userId, count);
        }
        return 1;
    }

    private JSONObject getAccountInfoByToken(String uid) {
        ResponseEntity<JSONObject> userInformation = dksApiController.getUserInformation(uid);
        return userInformation.getStatusCodeValue() == 200 ? userInformation.getBody() : null;
    }

    public void changeLoginState(String uid) {
        //根据 uid 修改登录状态
        daoTokenDksMapper.update(null,new LambdaUpdateWrapper<DaoTokenDks>().set(DaoTokenDks::getLoginStatus,0).eq(DaoTokenDks::getUid,uid));
    }

    public int getAccountCount(Integer userId) {
        return daoTokenDksService.getAccountCount(userId);
    }

    public int getAccountCount(String typeId, Integer userId) {
        return daoTokenDksService.getAccountCount(typeId, userId);
    }

    public PageInfo<DaoTokenDks> getAccountList(int pageNum, int pageSize, Integer userId) {
        return daoTokenDksService.getAccountList(pageNum, pageSize, userId);
    }

    public PageInfo<DaoTokenDks> getAccountList(int pageNum, int pageSize, String typeId, Integer userId) {
        return daoTokenDksService.getAccountList(pageNum, pageSize, typeId, userId);
    }









}

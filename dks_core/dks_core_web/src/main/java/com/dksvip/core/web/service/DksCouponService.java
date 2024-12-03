package com.dksvip.core.web.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dksvip.common.constant.ProxyParam;
import com.dksvip.common.exception.RxLoginCallException;
import com.dksvip.common.util.AppUtil;
import com.dksvip.core.dao.coupon.DaoCouponDks;
import com.dksvip.core.dao.coupon.service.DaoCouponDksService;
import com.dksvip.core.dao.token.DaoTokenDks;
import com.dksvip.core.dao.token.mapper.DaoTokenDksMapper;
import com.dksvip.core.web.controller.DksApiController;
import com.dksvip.core.web.redis.RedisUtil;
import jodd.util.MathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class DksCouponService {

    private final DksApiController dksApiController;
    private final DksTokenService dksTokenService;
    private final DaoCouponDksService daoCouponDksService;
    private final DaoTokenDksMapper daoTokenDksMapper;

    public Future<String> addCouponForUIds(String[] uIds, Integer userId) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        log.info("执行异步任务getCouponInfoKey：{}", Thread.currentThread().getName());
        int index = 1;
        for (String uid : uIds) {
            log.info("第 {} 个uid：{}", index++, uid);
            //根据token去获取优惠券信息
            int state = streamCoupon(uid, userId, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (state > 0) {
                //失败一次加1
                redisUtil.incr("dks:getCouponInfoKeyError:" + userId, 1);
            } else {
                //成功一次加1
                redisUtil.incr("dks:getCouponInfoKeySuccess:" + userId, 1);
            }
        }
        return new AsyncResult<>("Task addCouponForUIds completed");
    }

    private int streamCoupon(String uid, Integer userId, int count) {
        if (count >= 3) {
            return 1;
        }
        try {
            /*JSONObject obj = proxyService.getProxyInfo(url);
            JSONObject ipObj = obj.getJSONArray("data").getJSONObject(0);
            String str = obj.getJSONObject("data").getJSONArray("proxy_list").get(0).toString();
            String[] strArr = str.split(":");
            ProxyParam proxyParam = new ProxyParam();
            proxyParam.setIp("g560.kdltps.com");
            proxyParam.setPort(15818);
            ProxyParam proxyParam = new ProxyParam();
            proxyParam.setIp("61.183.41.73");
            proxyParam.setPort(38626);
            proxyParam.setUser("admin");
            proxyParam.setPassword("admin");*/
            // 获取优惠券
            JSONObject res = getCanUseCoupon(uid, null);
            System.out.println("res:      "  + res);
            // 优惠券入库
            streamCouponBusiness(uid, res, userId);
            return 0;
        } catch (RxLoginCallException e) {
            dksTokenService.changeLoginState(uid);
            log.info("uid {} 未登录，将uid表数改为未登录！", uid);
        } catch (Exception e) {
            e.printStackTrace();
            count = count + 1;
            log.info("同步券信息出现未知异常 {}，重试，第 {} 次：", e.getMessage(), count);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            streamCoupon(uid, userId, count);
        }
        return 1;
    }

    private JSONObject getCanUseCoupon(String uid, ProxyParam proxyParam) {
        System.out.println(uid);
        System.out.println(proxyParam);
        ResponseEntity<JSONObject> userCouponInfo = dksApiController.getUserCouponInfo("105039", 26, "ts", uid);
        return userCouponInfo.getStatusCodeValue() == 200 ? userCouponInfo.getBody().getJSONObject("data") : null;
    }

    private void streamCouponBusiness(String uid, JSONObject res, Integer userId) {
        // 先清除之前的券码
        daoCouponDksService.remove(userId,uid,null);
        // 将JSONArray转换为List<JSONObject>
        List<JSONObject> couponsList = res.getJSONArray("coupons").toList(JSONObject.class);

        // 使用流式操作过滤并转换数据 取出可用优惠券json数据
        JSONObject usableJsonObj = new JSONObject(
                couponsList.stream()
                        .map(AppUtil::objToJson) // 假设AppUtil::objToJson是将JSONObject转换为String的方法
                        .filter(json -> "usable".equals(json.getStr("tab_type")))
                        .findFirst()
                        .orElse(null));

        if (!JSONUtil.isNull(usableJsonObj)) {
            usableJsonObj.getJSONArray("list")
                .stream()
                .map(AppUtil::objToJson)
                .forEach(json -> {
                    System.out.println("json   " + json);
                    // 解析具体优惠券json 封装优惠券对象 插入数据库
                    DaoCouponDks daoCouponDks = new DaoCouponDks();
                    daoCouponDks.setId(IdUtil.getSnowflakeNextId());
                    daoCouponDks.setToken(uid);
                    daoCouponDks.setOpenId("111");
                    daoCouponDks.setTotalUseTimes(Integer.parseInt(json.getStr("num")));
                    daoCouponDks.setRemainUseTimes(1);
                    daoCouponDks.setStartTime(DateUtil.parse(json.getStr("begin_time"),"yyyy-MM-dd HH:mm:ss"));
                    daoCouponDks.setEndTime(DateUtil.parse(json.getStr("end_time"),"yyyy-MM-dd HH:mm:ss"));
                    daoCouponDks.setTicketId(json.getStr("ticket_id"));
                    daoCouponDks.setTicketCode(json.getStr("ticket_code"));
                    daoCouponDks.setTicketName(json.getStr("name"));
                    daoCouponDks.setStatus(1);
                    daoCouponDks.setUserId(userId);
                    daoCouponDks.setSillPrice(NumberUtil.div(new BigDecimal(json.getStr("sill_amt")),100));
                    daoCouponDks.setOriginalPrice(NumberUtil.div(new BigDecimal(json.getStr("ticket_amt")),100));
                    daoCouponDks.setSalePrice(daoCouponDks.getSillPrice().equals(new BigDecimal(0)) ? NumberUtil.div(daoCouponDks.getSillPrice().subtract(daoCouponDks.getOriginalPrice()),100) : NumberUtil.div(new BigDecimal(json.getStr("ticket_amt")),100));
                    daoCouponDks.setImageUrl(json.getStr("image_url"));
                    daoCouponDks.setDetailDescribe(json.getStr("title"));
                    daoCouponDks.setUsableTakeType(json.getStr("threshold_tags") == null ? "堂食专项" : CollUtil.join(json.getJSONArray("threshold_tags").toList(String.class), ","));
                    /*daoCouponDks.setPeriodStartTime(null);
                    daoCouponDks.setPeriodEndTime(null);
                    daoCouponDks.setMonthDays(null);
                    daoCouponDks.setRangeDays(null);
                    daoCouponDks.setWeekDays(null);
                    daoCouponDks.setSkuId(null);*/


                    daoCouponDksService.save(daoCouponDks);

                    // 如果 同步成功、则把该uid改成登录成功
                    DaoTokenDks daoTokenDks = daoTokenDksMapper.selectOne(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUid,uid).eq(DaoTokenDks::getUserId,userId).last("limit 1"));
                    if (ObjUtil.isNotEmpty(daoTokenDks)) {
                        daoTokenDks.setLoginStatus(1);
                        daoTokenDksMapper.updateById(daoTokenDks);
                    }
                });
        }
    }

}

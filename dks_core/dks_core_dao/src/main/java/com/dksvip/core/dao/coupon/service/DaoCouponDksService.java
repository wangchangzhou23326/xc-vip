package com.dksvip.core.dao.coupon.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dksvip.core.dao.coupon.DaoCouponDks;
import com.dksvip.core.dao.coupon.mapper.DaoCouponDksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaoCouponDksService {
    private final DaoCouponDksMapper daoCouponDksMapper;

    public void remove(Integer userId, String uid, Integer type) {
        daoCouponDksMapper.delete(new LambdaQueryWrapper<DaoCouponDks>().eq(DaoCouponDks::getUserId, userId).eq(DaoCouponDks::getToken,uid));
    }

    public void save(DaoCouponDks daoCouponDks) {
        daoCouponDksMapper.insert(daoCouponDks);
    }
}

package com.dksvip.core.dao.token.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dksvip.core.dao.token.DaoTokenDks;
import com.dksvip.core.dao.token.mapper.DaoTokenDksMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DaoTokenDksService{
    private final DaoTokenDksMapper daoTokenDksMapper;

    public PageInfo<DaoTokenDks> getAccountList(int pageNum, int pageSize, Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(daoTokenDksMapper.selectList(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUserId,userId).orderByDesc(DaoTokenDks::getCreateTime)));
    }

    public PageInfo<DaoTokenDks> getAccountList(int pageNum, int pageSize, String typeId, Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(daoTokenDksMapper.selectList(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUserId,userId).eq(DaoTokenDks::getUidTypeId,typeId).orderByDesc(DaoTokenDks::getCreateTime)));

    }

    public int getAccountCount(Integer userId) {
        //通过 userId获取数量
        return daoTokenDksMapper.selectCount(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUserId,userId)).intValue();
    }

    public int getAccountCount(String typeId, Integer userId) {
        //通过 userId 和 所属分类id 获取数量
        return daoTokenDksMapper.selectCount(new LambdaQueryWrapper<DaoTokenDks>().eq(DaoTokenDks::getUserId,userId).eq(DaoTokenDks::getUidTypeId,typeId)).intValue();
    }
}

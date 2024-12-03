package com.dksvip.core.web.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * MP的ID生成器
 */
public class MyIdGenerator implements IdentifierGenerator {
    private static final Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @Override
    public boolean assignId(Object idValue) {
        return IdentifierGenerator.super.assignId(idValue);
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }

    /**
     * 截取雪花算法的后16位作为ID
     */
    @Override
    public Number nextId(Object entity) {
        long id = snowflake.nextId();
        // 将生成的 ID 转换为 16 位数字，然后再转换为 Long 类型
        String idStr = String.valueOf(id);
        if (idStr.length() < 16) {
            //idStr = "0".repeat(16 - idStr.length()) + idStr;
            idStr = new String(new char[16 - idStr.length()]).replace("\0", idStr);
        } else if (idStr.length() > 16) {
            idStr = idStr.substring(idStr.length() - 16);
        }
        return Long.parseLong(idStr);
    }
}

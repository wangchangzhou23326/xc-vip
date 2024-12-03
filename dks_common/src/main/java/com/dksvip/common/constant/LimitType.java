package com.dksvip.common.constant;

/**
 * 限流类型
 *
 * @author
 */

public enum LimitType
{
    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}
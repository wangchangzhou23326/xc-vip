package com.dksvip.common.constant;

/**
 *   券的状态
 */
public interface CashCouponStatus {

    int NOT_USED = 0; //没有被使用

    int HAS_USED = 1; //已经用了

    int EXPIRED = -1; //过期

    int USED_ERROR = -2;
}

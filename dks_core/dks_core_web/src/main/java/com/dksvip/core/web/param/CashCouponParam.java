package com.dksvip.core.web.param;


import lombok.Data;

/**
 * @ClassName CashCouponVo
 * @Description TODO
 * @Author couqiu
 * @Date 2024/9/14 10:55
 **/
@Data
public class CashCouponParam {

    private Integer storeUserId;

    private String[] exchangeCodeStr;

}

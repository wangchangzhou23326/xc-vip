package com.dksvip.core.api.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author zhangcan
 * @date 2024/10/8 17:32
 */
@ApiModel("用户交易流水")
@Accessors(chain = true)
@Data
public class ApiUserDealLogVo {

    /**
     * 商户id
     */
    @ApiModelProperty("商户id")
    private Long storeId;
    /**
     * 交易类型(0 充值 1 订单支付 2 订单退款)
     */
    @ApiModelProperty("交易类型(0 充值 1 订单支付 2 订单退款)")
    private Integer dealType;
    /**
     * 兑换码
     */
    @ApiModelProperty("兑换码")
    private String vipCode;
    /**
     * 交易金额
     */
    @ApiModelProperty("交易金额")
    private BigDecimal dealMoney;
    /**
     * 交易后余额
     */
    @ApiModelProperty("交易后余额")
    private BigDecimal balance;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}

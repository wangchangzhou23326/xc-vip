package com.dksvip.core.api.dto.pay;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhang can
 * @date 2024-08-31 0:06
 */
@ApiModel("修改支付信息请求参数")
@Accessors(chain = true)
@Data
public class PayInfoParam {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 支付状态 0 失败，1 成功
     */
    private String payStatus;

}

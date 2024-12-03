package com.dksvip.core.api.dto.order;

import com.dksvip.core.api.dto.PacketInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * rx下单参数
 */
@ApiModel("下单参数")
@Accessors(chain = true)
@Data
public class ToOrderRxInputVo {

    @ApiModelProperty("sent派送  pick自提")
    private String delivery;


    private String eatway;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

    @ApiModelProperty("城市id")
    private String cityId;

    @ApiModelProperty("门店ID")
    private String deptId;

    @ApiModelProperty("兑换码")
    private String code;


    @ApiModelProperty("下单内容")
    private List<PacketInfo> packets;

    //商店用户名
    private String storeUserName;

}

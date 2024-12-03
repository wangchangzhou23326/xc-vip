package com.dksvip.core.api.dto.order;

import com.dksvip.core.api.dto.PacketInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@ApiModel("下单参数")
@Accessors(chain = true)
@Data
public class ToOrderInputVo {

    @ApiModelProperty("就餐方式：  店内就餐，打包带走，")
    private String eatTypeCode;


    @ApiModelProperty("商店code")
    private String storeCode;


    @ApiModelProperty("用户手机")
    private String userMobile;

    @ApiModelProperty("兑换码")
    private String code;


    @ApiModelProperty("下单内容")
    private List<PacketInfo> packets;

    @ApiModelProperty("车牌号")
    private String driverLicense;


    //商店用户名
    private String storeUserName;

}

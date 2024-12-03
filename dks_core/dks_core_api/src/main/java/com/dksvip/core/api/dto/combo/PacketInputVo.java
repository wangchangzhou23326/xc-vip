package com.dksvip.core.api.dto.combo;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
public class PacketInputVo {


    /**
     * 多个套餐id， 用，分隔
     */
//    private String packetIds;


    /**
     * 套餐选择id
     */
    private String selectionId;



    /**
     * 兑换码
     */
    private String code;


    /**
     * 商店编号
     */
    private String storeCode;



}

package com.dksvip.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
@Data
public class MealSelection {


//    selection ：选择 ->   packet ：组合  ->  product： -> comboItem


    private String selectionId;

    private String selectionName;

    /**
     * 每个 方案里 有多个组合
     */
    private List<PacketInfo> packetInfos;

}

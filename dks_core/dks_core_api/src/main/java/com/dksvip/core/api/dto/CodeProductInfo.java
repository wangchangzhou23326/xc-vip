package com.dksvip.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


@Accessors(chain = true)
@Data
public class CodeProductInfo {

    private String code;

    private Integer codeStatus;

    private String selectionId;

    private String selectionName;

    private List<PacketInfo> packetInfos;

    /**
     * 门店类型0=普通门店，1=溢价门店
     */
    private Integer storeType = 0;

    public void addPacket(PacketInfo packetInfo) {
        if (packetInfos == null) {
            packetInfos = new ArrayList<PacketInfo>();
        }
        packetInfos.add(packetInfo);
    }


}

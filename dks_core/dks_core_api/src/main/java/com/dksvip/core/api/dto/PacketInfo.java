package com.dksvip.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalTime;
import java.util.List;


@Accessors(chain = true)
@Data
public class PacketInfo {
    /**
     * 套餐id
     */
    private Long packetId;

    /**
     * 套餐数量
     */
    private Integer num;


    /**
     * 商品
     */
    private List<ProductInfo> products;



    /**
     * 套餐标题
     */
    private String name ;


    /**
     * 套餐图片
     */
    private String image;

    //套餐开始时间
    private LocalTime startTime;

    //套餐结束时间
    private LocalTime endTime;
}

package com.dksvip.core.api.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Accessors(chain = true)
public class DtoOrderInfo {


    private Long vipOrderId;

    private String storeName;

    private String storeCode;

    private String pickupCode;

    private String takeOrderId;

    private String orderContent;


    private Integer orderStatus;
    private String orderStatusName;

    private String errorMsg;

    private Date updateTime;
    private Date createTime;



    private String packetName;
    private String packetImg;

    private Long packetId;


    private BigDecimal price;

    private String vipCode;

    private String orderInfo;



    private String orderId;


}

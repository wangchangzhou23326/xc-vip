package com.dksvip.core.api.dto.gen;

import com.dksvip.core.api.dto.order.DtoOrderInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class DtoVipCode {



    private String title;


    private String msgTemplate;

    private String code;

    private String domain;

    private String recordId;


    private Date createTime;

    private Date endTime;

    private List<DtoOrderInfo> orders;

}

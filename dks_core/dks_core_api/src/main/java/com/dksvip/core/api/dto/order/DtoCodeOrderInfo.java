package com.dksvip.core.api.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


@Accessors(chain = true)
@Data
public class DtoCodeOrderInfo {


    private String code;


    private Integer codeStatus;
    private String codeStatusName;

    private List<DtoOrderInfo> orders;

    public void addOrder(DtoOrderInfo orderInfo) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(orderInfo);
    }


}

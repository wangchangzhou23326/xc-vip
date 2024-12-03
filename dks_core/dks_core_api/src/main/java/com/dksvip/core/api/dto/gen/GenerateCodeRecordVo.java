package com.dksvip.core.api.dto.gen;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@ApiModel("生成兑换码vo")
@Data
@Accessors(chain = true)
public class GenerateCodeRecordVo {



    /**
     * 91 卡券：
     *  发送倍数 1     购买数量 n x1      调用 发卡接口 n次    返回n个兑换码
     *
     *  发送倍数1:    购买数量
     */


    //    private Integer storeUserId;


    private String source;


    //淘宝订订单号
    private String tbTid;

    //商店用户名
    private String storeUserName;

//    private Integer platformId;




    //生成数量   商户直接生成兑换码    91卡券不会传这个
    private Integer cdkNum;


    //套餐名
    private String title;


    //套餐 (商品编码)多个用，分隔
    private String setmeals;



    //生成code
    private List<DtoVipCode> codes;

    //兑换码分类
    private String type;
}

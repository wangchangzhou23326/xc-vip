package com.dksvip.core.api.dto;

import cn.hutool.json.JSONObject;
import lombok.NoArgsConstructor;


//@Data
//@Accessors(chain = true)
@NoArgsConstructor
public class ProductInfo  extends JSONObject {


    public ProductInfo(JSONObject obj, Integer productId){
        super(obj);
        this.set("productId", productId);
    }




    /**
     * 商品id
     */
//    private Integer productId;

//    /**
//     * 商品code
//     */
//    private String productCode;
//
//
//
//    /**
//     * 商品名称
//     */
//    private String productName;
//
//    private String longName;
//
//    private String desc;
//
//    private String image;
//
//    /**
//     * 商品类型
//     */
//    private Integer productType;
//
//
//
//    /**
//     * 组合
//     */
////    private List<ComboItem> comboItems;
//    private JSONArray comboItems;




}

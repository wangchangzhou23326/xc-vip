package com.dksvip.core.dao.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_product_info_dks")
@Data
public class DaoProductInfoDks {
    /**
     * 主键 id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品图片
     */
    @TableField("main_image_url")
    private String mainImageUrl;

    /**
     * 商品套餐id
     */
    @TableField("product_package_id")
    private Long productPackageId;

    /**
     * 套餐官方id
     */
    @TableField("system_id")
    private String systemId;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 真实价格
     */
    @TableField("real_price")
    private BigDecimal realPrice;

    /**
     * 店铺code
     */
    @TableField("store_code")
    private String storeCode;

    /**
     * 是否为默认选中  0是  1否
     */
    @TableField("is_default")
    private Integer isDefault;

    /**
     * 分组标识
     */
    @TableField("round_id")
    private String roundId;

    /**
     * 分组名称
     */
    @TableField("round_name")
    private String roundName;

    /**
     * 套餐必选小件
     */
    @TableField("product_attributes")
    private String productAttributes;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 基础价格
     */
    @TableField("round_base_price")
    private BigDecimal roundBasePrice;

    /**
     * 基础价格
     */
    @TableField("round_base_real_price")
    private BigDecimal roundBaseRealPrice;

    /**
     * 是否必须选中商品   0是 1否
     */
    @TableField("include_round")
    private Integer includeRound;

    /**
     * 创建用户id
     */
    @TableField("user_id")
    private Long userId;

}

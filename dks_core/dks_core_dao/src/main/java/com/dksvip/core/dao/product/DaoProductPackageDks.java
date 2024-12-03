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

@TableName("t_product_package_dks")
@Data
public class DaoProductPackageDks {
    /**
     * 主键 id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 菜单分类id
     */
    @TableField("class_ext_id")
    private String classExtId;

    /**
     * 子类型id
     */
    @TableField("child_class_id")
    private String childClassId;

    /**
     * 子类型名称
     */
    @TableField("child_class_name")
    private String childClassName;

    /**
     * 官方套餐Id
     */
    @TableField("system_id")
    private String systemId;

    /**
     * 店铺code
     */
    @TableField("store_code")
    private String storeCode;

    /**
     * 套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 真实价格
     */
    @TableField("real_price")
    private BigDecimal realPrice;

    /**
     * 官方价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 类类型
     */
    @TableField("class_type")
    private Integer classType;

    /**
     * 卡类型
     */
    @TableField("card_type")
    private Integer cardType;

    /**
     * 套餐类型
     */
    @TableField("product_category")
    private Integer productCategory;

    /**
     * 是否有选项
     */
    @TableField("is_option")
    private Integer isOption;

    /**
     * 套餐描述
     */
    @TableField("product_package_desc")
    private Integer productPackageDesc;

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

}

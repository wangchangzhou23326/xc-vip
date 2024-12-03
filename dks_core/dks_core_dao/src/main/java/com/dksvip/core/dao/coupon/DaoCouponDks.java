package com.dksvip.core.dao.coupon;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_coupon_dks")
@Data
public class DaoCouponDks {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * token
     */
    @TableField("token")
    private String token;

    /**
     * 会员id
     */
    @TableField("openid")
    private String openId;

    /**
     * 总使用次数
     */
    @TableField("total_use_times")
    private int totalUseTimes;

    /**
     * 剩余使用次数
     */
    @TableField("remain_use_times")
    private int remainUseTimes;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 失效时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 券id
     */
    @TableField("ticket_id")
    private String ticketId;

    /**
     * 券编码
     */
    @TableField("ticket_code")
    private String ticketCode;

    /**
     * 券名称
     */
    @TableField("ticket_name")
    private String ticketName;

    /**
     * 使用状态：1.可使用 2.已被使用 0.使用失败
     */
    @TableField("status")
    private int status;

    /**
     * 用户id
     */
    @TableField("user_id")
    private int userId;

    /**
     * 原本消费价值
     */
    @TableField("sill_price")
    private BigDecimal sillPrice;


    /**
     * 原本消费价值
     */
    @TableField("original_price")
    private BigDecimal originalPrice;

    /**
     * 用券消费价值
     */
    @TableField("sale_price")
    private BigDecimal salePrice;

    /**
     * 券图片信息
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 券描述
     */
    @TableField("detail_describe")
    private String detailDescribe;

    /**
     * 每天开始使用时间
     */
    @TableField("period_start_time")
    private Date periodStartTime;

    /**
     * 每天结束使用时间
     */
    @TableField("period_end_time")
    private Date periodEndTime;

    /**
     * 点餐方式，堂食外卖等 用逗号隔开
     */
    @TableField("usable_take_type")
    private String usableTakeType;

    /**
     * 某月份的某一天可用
     */
    @TableField("month_days")
    private String monthDays;

    /**
     * 不详
     */
    @TableField("range_days")
    private String rangeDays;

    /**
     * 每周的星期几可用
     */
    @TableField("week_days")
    private String weekDays;

    /**
     * 券的sku
     */
    @TableField("sku_id")
    private Integer skuId;

}

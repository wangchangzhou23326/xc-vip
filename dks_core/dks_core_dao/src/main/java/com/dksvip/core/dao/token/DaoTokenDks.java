package com.dksvip.core.dao.token;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_token_dks")
@Data
public class DaoTokenDks {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * uid token
     */
    @TableField("uid")
    private String uid;

    /**
     * 所属用户id
     */
    @TableField("user_id")
    private int userId;

    /**
     * 状态0=未使用，1=已锁定，2=下单中
     */
    @TableField("status")
    private int status;

    /**
     * 是否登录0否1是
     */
    @TableField("login_status")
    private int loginStatus;

    /**
     * 账户余额
     */
    @TableField("balance")
    private BigDecimal balance;

    /**
     * 钱包余额
     */
    @TableField("money_bag")
    private BigDecimal moneyBag;

    /**
     * 账号分类ID
     */
    @TableField("uid_type_id")
    private int uidTypeId;

    /**
     * 所属分类名称
     */
    @TableField("uid_type_name")
    private String uidTypeName;

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
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

}

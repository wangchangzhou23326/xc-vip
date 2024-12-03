package com.dksvip.core.web.param;


import lombok.Data;

/**
 * @ClassName OpenWalletSetPasswordParam
 * @Description TODO
 * @Author couqiu
 * @Date 2024/9/11 9:56
 **/
@Data
public class OpenWalletSetPasswordParam {

    private String password;
    private String[] sidList;
}

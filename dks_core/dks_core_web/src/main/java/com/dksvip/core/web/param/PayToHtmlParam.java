package com.dksvip.core.web.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 参数转H5 请求参数
 */
@Data
public class PayToHtmlParam implements Serializable {
    /**
     * 需要转的字符串
     */
    private String appPayStr;
}

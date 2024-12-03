package com.dksvip.common.exception;


/**
 * 瑞幸异常处理类
 * @author gonghuizhou
 */
public class RxLoginCallException extends AppException {
    public RxLoginCallException(String message) {
        super(message);
        this.code = ExceptionCodes.RX_LOGIN_CALL_EXCEPTION;
    }
}

package com.dksvip.common.exception;


/**
 * 瑞幸异常处理类
 * @author gonghuizhou
 */
public class RxCallException extends AppException {
    public RxCallException(String message) {
        super(message);
        this.code = ExceptionCodes.RX_CALL_EXCEPTION;
    }
}

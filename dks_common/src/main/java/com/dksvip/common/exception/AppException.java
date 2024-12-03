package com.dksvip.common.exception;



public class AppException extends RuntimeException{
    protected int code = ExceptionCodes.FAILED;
    protected Object data = null;

    public int getCode() {
        return code;
    }

    public Object getData(){
        return data;
    }

    public AppException(String message){
        super(message);
    }
    public AppException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public AppException(String message, Object data){
        super(message);
        this.data = data;
    }


}

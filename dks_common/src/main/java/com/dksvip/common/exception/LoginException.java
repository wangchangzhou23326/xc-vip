package com.dksvip.common.exception;


public class LoginException extends AppException{


    public LoginException(String message){
        super(message);
        this.code = ExceptionCodes.APP_NEED_LOGIN;
    }

}

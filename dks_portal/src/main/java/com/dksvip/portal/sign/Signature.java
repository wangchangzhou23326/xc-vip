package com.dksvip.portal.sign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pdai
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature {

    /**
     * 签名校验类型,0=需要校验token，1=不需要校验token,不需要校验密钥
     */
    int type() default 0;

}
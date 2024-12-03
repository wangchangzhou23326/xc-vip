package com.dksvip.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeInfo {

    String value()  default "" ;

    String name() default "";

    String  defaultValue()  default "" ;

    Class type() ;

    boolean enable() default true;

}

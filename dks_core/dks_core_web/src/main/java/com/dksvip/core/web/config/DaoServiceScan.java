package com.dksvip.core.web.config;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DaoServiceBeanDefinitionRegister.class})
public @interface DaoServiceScan {

    @AliasFor("basePackages")
    String[] value()  default {} ;

    @AliasFor("value")
    String[] basePackages() default {};
}
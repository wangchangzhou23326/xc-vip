package com.dksvip.core.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author
 * @date 2024/7/23 14:43
 * @description
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.dksvip.core.api.*", "com.dksvip.core.dao.*", "com.dksvip.core.decrypt.*", "com.dksvip.core.web"})
@MapperScan("com.dksvip.core.dao.*")
public class DksVipCoreWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DksVipCoreWebApplication.class, args);
    }
}
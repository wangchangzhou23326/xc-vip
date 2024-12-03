package com.dksvip.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author
 * @date 2024/7/25 13:42
 * @description
 */
@EnableFeignClients(basePackages = "com.dksvip.core.api")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {
        "com.dksvip.portal"
})
public class DksVipPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DksVipPortalApplication.class, args);
    }

}

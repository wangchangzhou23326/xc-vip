package com.dksvip.core.web.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConf {
    /**
     * 自定义MP的主键生成策略
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        return new MyIdGenerator();
    }
}

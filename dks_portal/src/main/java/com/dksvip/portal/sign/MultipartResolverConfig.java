package com.dksvip.portal.sign;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author zhang can
 * @date 2024-08-28 22:32
 */
@Configuration
public class MultipartResolverConfig {
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 128MB
        multipartResolver.setMaxUploadSize(128 * 1024 * 1024);
        // 不保留在内存中
        multipartResolver.setMaxInMemorySize(0);
        // 延迟解析文件
        multipartResolver.setResolveLazily(true);
        return multipartResolver;
    }
}

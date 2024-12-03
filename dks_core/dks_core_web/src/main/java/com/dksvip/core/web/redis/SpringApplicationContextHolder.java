package com.dksvip.core.web.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringApplicationContextHolder
 *
 * @author
 * @since 2024-09-06
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationContextHolder.context = context;
    }

    public static Object getSpringBean(String beanName) {
        return context == null ? null : context.getBean(beanName);
    }

    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }

    /**
     * 获取当前的profile
     *
     * @return
     */
    /*public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }*/

    /**
     * 是否为测试环境
     *
     * @return
     */
    /*public static boolean getDevMode() {
        String env = getActiveProfile();
        return "dev".equalsIgnoreCase(env);
    }*/

}

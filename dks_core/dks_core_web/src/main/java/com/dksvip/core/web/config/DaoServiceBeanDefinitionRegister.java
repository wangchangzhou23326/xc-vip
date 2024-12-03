
package com.dksvip.core.web.config;

import cn.hutool.core.lang.ClassScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class DaoServiceBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
 
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(DaoServiceScan.class.getName()));
        if(annoAttrs != null) {
            String[] stringArray = annoAttrs.getStringArray("value");
            for (String packageName : stringArray) {
                Set<Class<?>> scan = ClassScanner.scanAllPackage("com.rxvip.core.dao",
                        aClass -> aClass.getName().toString().contains("service"));
                for (Class<?> aClass : scan) {
                    if(aClass.isInterface() || aClass.isAnonymousClass() || aClass.isEnum()) {
                        continue;
                    }
                    registry.registerBeanDefinition(aClass.getName(), new RootBeanDefinition(aClass));
                }
            }
        }
    }
}
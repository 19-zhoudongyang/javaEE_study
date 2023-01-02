package com.zhou.importBeanDefinitionRegistrar;

import com.zhou.testBeanDemo01;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //AnnotationMetadata:当前类的注解信息

    /**
     * BeanDefinitionRegistry:BeanDefinition注册类，
     * 把需要添加到容器中的bean，调用BeanDefinitionRegistry.registerBeanDefinition手工注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取此bean在IOC容器中是否存在
        boolean b = registry.containsBeanDefinition("com.zhou.conditional.WindowsConditional");
        //如果com.zhou.conditional.WindowsConditional在容器中存在，则将testBeanDemo01类注册进IOC
        if (b) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(testBeanDemo01.class);
            registry.registerBeanDefinition(/*指定bean名*/"demo01", rootBeanDefinition);
        }
    }
}

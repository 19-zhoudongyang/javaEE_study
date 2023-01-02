package com.zhou.mvc.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    //初始化之前调用
    @Override
    public Object postProcessBeforeInitialization(/*初始化的实例*/Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前");
        return bean;
    }

    //初始化完毕后调用
    @Override
    public Object postProcessAfterInitialization(/*初始化的实例*/Object bean, String beanName) throws BeansException {
        System.out.println("初始化完成");
        return bean;
    }
}

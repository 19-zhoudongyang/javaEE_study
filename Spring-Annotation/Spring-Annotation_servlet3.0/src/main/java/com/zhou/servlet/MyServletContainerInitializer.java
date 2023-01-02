package com.zhou.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;


@HandlesTypes(value = {HelloService.class})
/*
注解作用
    容器启动时会将@HandlesTypes指定的这个类型及其子类型(子接口、实现类等)传递到方法onStartup的set集合里
    传入感兴趣的类型
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }
    }
}

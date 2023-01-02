package com.zhou;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class testBeanDemo05 {

    @PostConstruct
    public void Init() {
        System.out.println("初始化demo05。。。。。。。。。。。。。。。。");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁demo05.....");
    }
}

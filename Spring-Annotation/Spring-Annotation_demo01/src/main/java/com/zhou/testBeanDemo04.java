package com.zhou;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//@Component
public class testBeanDemo04 implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁.....");
    }
}

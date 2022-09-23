package com.zhou.factory;

import com.zhou.testBeanDemo02;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<testBeanDemo02> {

    //返回一个testBeanDemo02对象，注入到IOC容器中
    @Override
    public testBeanDemo02 getObject() throws Exception {
        return new testBeanDemo02();
    }

    @Override
    public Class<?> getObjectType() {
        return testBeanDemo02.class;
    }

    //返回true表示单实例，返回false代表多实例
    @Override
    public boolean isSingleton() {
        return true;
    }
}

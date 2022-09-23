package com.zhou.main;

import com.zhou.mvc.config.SpringConfig;
import com.zhou.mvc.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //ioc根据类型自动装配
        Person bean = applicationContext.getBean(Person.class);
        //ioc根据名称实现装配，名称默认为配置类里对应的方法名
        Person person =(Person) applicationContext.getBean("person");
        System.out.println(bean);
        System.out.println(person);
    }
}

package com.zhou;

public class testBeanDemo03 {
    public testBeanDemo03(){
        System.out.println("构造器....");
    }
    public void init(){
        System.out.println("初始化方法....");
    }
    public void destroy(){
        System.out.println("销毁方法....");
    }
}

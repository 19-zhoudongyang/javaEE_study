package com.zhou.mvc;

import com.zhou.aop.LogAspects;
import com.zhou.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//开启基于注解的AOP模式
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("com.zhou")
public class config1 {}

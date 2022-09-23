package com.zhou.mvc.config;

import com.zhou.conditional.LinuxConditional;
import com.zhou.conditional.WindowsConditional;
import com.zhou.factory.MyFactoryBean;
import com.zhou.importBeanDefinitionRegistrar.MyImportBeanDefinitionRegistrar;
import com.zhou.importSelect.MyImportSelect;
import com.zhou.mvc.pojo.Person;
import com.zhou.testBeanDemo03;
import com.zhou.testBeanDemo04;
import org.springframework.context.annotation.*;

@PropertySource("classpath:testValue.properties")
@Configuration
@ComponentScan("com.zhou")
@Import({WindowsConditional.class, MyImportSelect.class, MyImportBeanDefinitionRegistrar.class})
public class SpringConfig2 {
    @Conditional({LinuxConditional.class})
    @Scope("session")
    @Bean
    public Person person(){
        return new Person("zhou",33);
    }
    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }
//    @Bean(initMethod = "init",destroyMethod = "destroy")
//    public testBeanDemo03 Demo03(){
//        return new testBeanDemo03();
//    }
}


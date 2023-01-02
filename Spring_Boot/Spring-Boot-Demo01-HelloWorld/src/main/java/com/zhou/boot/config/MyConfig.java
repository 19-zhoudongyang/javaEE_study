package com.zhou.boot.config;

import com.zhou.boot.bean.Pet;
import com.zhou.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration:标注的类为SpringBoot配置类，配置类取代配置文件
 */

@Configuration
public class MyConfig {

    /*
    给容器添加组件，以方法名作为组件的id。返回类型就是组件类型，返回的值就是在容器中的实例
     */
    @Bean
    public User user01() {
        return new User("张三", 18);
    }

    @Bean
    public Pet Tom() {
        return new Pet("Tom");
    }
}

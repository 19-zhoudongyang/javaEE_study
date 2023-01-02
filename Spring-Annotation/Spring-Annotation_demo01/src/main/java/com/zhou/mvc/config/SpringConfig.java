package com.zhou.mvc.config;

import com.zhou.mvc.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
        value = "com.zhou.mvc",
        excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = Controller.class)},
        includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = Repository.class),
                @Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)},
        useDefaultFilters = false)
public class SpringConfig {
    @Bean
    public Person person() {
        return new Person("zhou", 22);
    }
}

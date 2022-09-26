package com.zhou.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootDemo02WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo02WebApplication.class, args);
    }

}

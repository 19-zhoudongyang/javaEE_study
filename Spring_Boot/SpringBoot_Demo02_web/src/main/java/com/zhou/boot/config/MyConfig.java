package com.zhou.boot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.zhou.boot.converter.ZhouMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Configuration
public class MyConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                //添加自定义Converter
                converters.add(new ZhouMessageConverter());
            }

            /*
            自定义内容协商策略
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                HashMap<String, MediaType> hashMap = new HashMap<>();
                //指定支持解析哪些参数对应的媒体类型
                hashMap.put("json", MediaType.APPLICATION_JSON);
                hashMap.put("xml", MediaType.APPLICATION_XML);

                //浏览器参数format=gg
                hashMap.put("gg", MediaType.parseMediaType("application/zhou"));

                //参数方式
                ParameterContentNegotiationStrategy strategy1 = new ParameterContentNegotiationStrategy(hashMap);
                //将浏览器参数format=gg改为ff=gg
                strategy1.setParameterName("ff");

                //请求头方式
                HeaderContentNegotiationStrategy strategy2 = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(strategy1, strategy2));
            }
        };
    }

//    /*
//    配置druid的监控页功能
//     */
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        StatViewServlet statViewServlet = new StatViewServlet();
//        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
//                new ServletRegistrationBean<>(statViewServlet,"/druid/* ");
//        return servletRegistrationBean;
//    }
}

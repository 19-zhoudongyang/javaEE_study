package com.zhou.mvc.config;

import com.zhou.mvc.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

@Configuration
//开启组件扫描
@ComponentScan("com.zhou.mvc.controller")
//开启mvc注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //开启对静态资源的访问(开启默认的servlet)
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
            /** addPathPatterns()设置需要拦截的请求
             *    /*代表拦截所有请求(只有一层目录匹配)，
             *    /**代表拦截所有请求(多层目录也可匹配)
            */
            /** excludePathPatterns设置不需要拦截的请求，
             *    /代表不拦截路径为/的请求
            */
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("/");
    }

    //view-contorller(视图控制器)
    /**
     * addViewController("/")表示路径为/
     * setViewName("index")表示视图名为index
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    //配置文件上传解析器
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    // 配置自定义异常处理器
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        //设置要捕获的异常类型和渲染的页面
        properties.setProperty("java.lang.ArithmeticException","error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        //将异常信息保存到请求域中,key为ex
        simpleMappingExceptionResolver.setExceptionAttribute("ex");
        resolvers.add(simpleMappingExceptionResolver);

    }

    //配置视图解析器
    //配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver(){
        WebApplicationContext currentWebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(currentWebApplicationContext.getServletContext());
        servletContextTemplateResolver.setPrefix("/WEB-INF/templates/");
        servletContextTemplateResolver.setSuffix(".html");
        servletContextTemplateResolver.setCharacterEncoding("UTF-8");
        servletContextTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return servletContextTemplateResolver;
    }
    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);
        return springTemplateEngine;
    }
    //生成视图解析器并为解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);
        return thymeleafViewResolver;
    }
}

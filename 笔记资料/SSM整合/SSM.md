# 一、搭建
>- (一)导入依赖

        <properties>
            <spring.version>5.3.22</spring.version>
        </properties>
    
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-web</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>
    
            <!--mybatis核心-->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.5.7</version>
            </dependency>
    
            <!--mybatis和spring的整合包-->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>2.0.6</version>
            </dependency>
    
            <!--连接池-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.2.10</version>
            </dependency>
    
            <!--junit测试-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
                <scope>test</scope>
            </dependency>
    
            <!--mysql驱动-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.30</version>
            </dependency>
    
            <!--log4j日志-->
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>1.2.17</version>
            </dependency>
    
            <!--分页插件pagehelper的依赖-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>5.2.0</version>
            </dependency>
    
            <!--日志-->
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>1.2.11</version>
            </dependency>
    
            <!--servletAPI-->
            <dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>servlet-api</artifactId>
                <version>2.5</version>
            </dependency>
    
            <!--json依赖-->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.13.3</version>
            </dependency>
    
            <!--文件上传依赖-->
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>1.3.1</version>
            </dependency>
    
            <!--spring5和Thymeleaf整合包-->
            <dependency>
                <groupId>org.thymeleaf</groupId>
                <artifactId>thymeleaf-spring5</artifactId>
                <version>3.0.12.RELEASE</version>
            </dependency>
        </dependencies>
        
>- (二)创建webapp文件夹和子目录WEB-INF以及文件web.xml
>>- ![1](ssm_pic/ssm01.png)

>- (三)配置web.xml文件
    
    <!--配置spring编码过滤器-->
        <filter>
            <filter-name>CharacterEncodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <!--设定请求的编码-->
            <init-param>
                <param-name>encoding</param-name>
                <param-value>UTF-8</param-value>
            </init-param>
            <!--设置响应的编码-->
            <init-param>
                <param-name>forceEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>CharacterEncodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
    
        <!--配置处理请求方式的过滤器-->
        <filter>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
    
        <!--配置SpringMVC的前端控制器DispatcherServlet-->
        <servlet>
            <servlet-name>SpringMVC</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <!--配置SpringMVC配置文件的位置和名称-->
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:springmvc.xml</param-value>
            </init-param>
            <!--将DispatcherServlet初始化时间提前到服务器启动时-->
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>SpringMVC</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
    
        <!--配置spring的监听器，在服务器启动时加载Spring的配置文件-->
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>
    
        <!--设置Spring配置文件自定义的位置和名称-->
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring.xml</param-value>
        </context-param>
>- (四)配置数据库(创建表)

>- (五)配置SpringMVC
>>- 1.配置SpringMVC的核心配置文件(springmvc.xml)

    <!--扫描控制层组件-->
        <context:component-scan base-package="com.zhou.ssm.controller"></context:component-scan>
    
        <!--配置视图解析器-->
        <!--Thymeleaf视图解析器    -->
        <bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
            <!--设置试图解析器的优先级(可以使用多个视图解析器-->
            <property name="order" value="1"/>
            <property name="characterEncoding" value="UTF-8"/>
            <property name="templateEngine">
                <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                    <property name="templateResolver">
                        <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                            <!--  视图前缀-->
                            <property name="prefix" value="/WEB-INF/templates/"/>
                            <!--  视图后缀-->
                            <property name="suffix" value=".html"/>
                            <!--  页面模板 -->
                            <property name="templateMode" value="HTML5"/>
                            <property name="characterEncoding" value="UTF-8"/>
                        </bean>
                    </property>
                </bean>
            </property>
        </bean>
    
        <!--配置默认的servlet处理静态资源-->
        <mvc:default-servlet-handler />
    
        <!--开启mvc的注解驱动-->
        <mvc:annotation-driven />
        
        <!--配置视图控制器-->
        <mvc:view-controller path="/" view-name="index"></mvc:view-controller>
    
        <!--配置文件上传解析器-->
        <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>
>>- 2.创建控制层(controller)和控制器
>>- 3.部署到Tomcat服务器中

>- (六)配置spring
>>- 1.在resource资源目录下创建Spring配置文件(spring.xml)并配置此文件
>>>- 配置数据源需要创建和配置properties文件

        <!--扫描组件(扫描除了控制层外的其他层)-->
        <context:component-scan base-package="com.zhou.ssm">
            <!--排除控制层-->
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    
        <!--引入jdbc.properties-->
        <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
        <!--配置数据源-->
        <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
            <property name="driverClassName" value="${driverClass}"></property>
            <property name="url" value="${url}"></property>
            <property name="username" value="${username}"></property>
            <property name="password" value="${password}"></property>
        </bean>
>>- 2.创建业务层(service)和对应的接口和实现类(impl)

>- (八)配置mybatis
>>- 1.配置核心配置文件(mybatis.xml)

    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//com.zhou.mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        
        <!--全局配置-->
        <settings>
            <!--将下划线映射为驼峰-->
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>   
        
        <!--设置类型别名-->
        <typeAliases>
            <!--不设置alias则默认别名为类名且不区分大小写-->
            <typeAlias type="" alias=""></typeAlias>
            <!--为包下所有的类起别名，默认别名为类名且不区分大小写-->
            <package name=""/>
        </typeAliases>
    
        <!--配置分页插件-->
        <plugins>
            <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
        </plugins> 
    
        <!--引入映射文件-->
        <mappers>
            <!--在resource资源目录中以支持方式引入映射文件-->
            <!--<mapper resource="mappers/.xml"/>-->
    
            <!--在resource资源目录中以包的形式引入映射文件，路径需要与对应的接口的包路径一致-->
            <package name=""/>
        </mappers>
    </configuration>
    
>>- 2.创建mapper层和对应的mapper接口，以及在resource资源目录下创建相同的目录结构的mapper文件夹和对应的映射文件(需要和对应的接口名同名)
>>>- ![1](ssm_pic/ssm02.png)
>>>- 配置映射文件

    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//com.zhou.mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="">
    
    </mapper>
    
>>- 3.创建pojo实体层和实体类
>>- 4.在spring配置文件中(spring.xml)，配置SelSessionFactoryBean，这样便可以在IOC容器中直接获取SqlSessionFactory

    <!--整合mybatis-->
    <!--配置SqlSessionFactoryBean，可以在Spring的IOC容器中直接获取SqlSessionFactory-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--设置当前mybatis的核心配置文件的路径-->
        <property name="configLocation" value="classpath:mybatis.config.xml"></property>
    </bean>
    
    <!--配置mapper接口的扫描，将指定包下的所有mapper接口，
    通过SqlSession创建代理实现类对象，
    并将这些对象交给IOC容器管理-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.zhou.ssm.mapper"></property>
        <!--需要弃用mybatis核心配置文件中的数据库连接环境-->
        <property name="dataSource" ref="dataSource"></property> 
    </bean>
    
>- (九)创建log4j.xml日志配置文件
    
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
        <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
            <param name="Encoding" value="UTF-8"/>
            <layout class="org.apache.log4j.PatternLayout">
                <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m (%F:%L) \n"/>
            </layout>
        </appender>
        <logger name="java.sql">
            <level value="debug"/>
        </logger>
        <logger name="org.apache.ibatis">
            <level value="info"/>
        </logger>
        <root>
            <level value="debug"/>
            <appender-ref ref="STDOUT"/>
        </root>
    </log4j:configuration>
    
>- (十)配置事务管理
>>- 1.注解方式:在spring的配置文件中(spring.xml)配置，将使用注解@Transactional标识的方法或类中所有的方法进行事务管理
        
     <!--配置事务管理器-->
     <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
         <!--引用数据源-->
         <property name="dataSource" ref="dataSource"></property>
     </bean>
     <!--
         开启事务的注解驱动
         将使用注解@Transactional标识的方法或类中所有的方法进行事务管理
     -->
     <tx:annotation-driven transaction-manager="transactionManager
     

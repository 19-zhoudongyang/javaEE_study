一、Spring是轻量级的开源JavaEE框架
    [1](./spring_pic/spring%E7%9A%84%E7%BB%93%E6%9E%84.PNG)
二、IOC(控制反转，把创建对象的过程交给Spring进行管理)
    1.底层结构
        (1)IOC接口=====BeanFactory
    2.IOC接口管理Bean操作======详见第五节第一小节------IOC操作Bean管理
    3.目的：降低类之间的耦合度
    4.底层原理：xml解析、工厂模式、反射
    5.IOC容器：
        (1)IOC思想基于IOC容器完成，IOC容器底层就是对象工厂
        (2)Spring提供IOC容器的两种实现方式
            [1]beanFactory接口：
                {1}IOC基本实现，是Spring内部的使用接口，一般不提供给开发人员使用
                {2}加载配置文件时不会创建对象，获取对象时才创建对象，即使用getBean方法时才会创建对象
            [2]ApplicationContext接口：
                {1}BeanFactory接口的子接口，提供更强大的功能，一般供开发人员使用
                {2}加载配置文件时就会创建对象
                {3}此接口的实现类：
                    *1.ClassPathXmlApplicationContext：构造器参数填写类路径
                    *2.FileSystemXmlApplicationContext：构造器参数填写绝对路径
三、Aop(面向切面编程)
    (一)描述：不修改源代码进行功能增强
    (二)底层原理：动态代理
        1.动态代理有两种情况：
            (1)有接口的情况：使用JDK动态代理
                [1]已知存在接口UserDao和其实现类UserDaoImpl
                [2]创建实现类的代理类实现功能增强
            (2)没有接口的情况：使用CGLIB动态代理
                [1]已知存在一个User类和子类Person类
                [2]创建子类Person类的代理类
        2.使用java.lang包下的Proxy类实现动态代理(JDK动态代理)
            (1)核心：调用此类的Object newProxyInstance(ClassLoader loader,class<?>[] interfaces,InvocationHandler h)方法
                三个参数
                    [1]ClassLoader loader:类加载器
                    [2]class<?>[] interfaces:增强方法所在的类，这个类实现得接口，因为是数组接收，所以支持多个接口
                    [3]InvocationHandler h:实现这个接口InvocationHandler，创建代理对象，写增强的方法
            (2)使用：
                [1]创建接口，定义方法
                [2]创建接口实现类，实现方法
                [3]创建Proxy代理类，创建Object类型的属性obj，创建其构造方法接收实参
                [4]在某个方法里(比如JDKProxy)调用Proxy.newProxyInstance(ClassLoader loader,class<?>[] interfaces,InvocationHandler h)方法
                    Proxy.new ProxyInstance(此代理类名.class.getClassLoader(),接口实现类的接口名.class,new InvocationHandler(){
                        @Override
                        public Object invoke(Object proxy,Method method,Object[] args){
                            <!-- 方法执行之前的增强部分 -->
                            ...
                            <!-- 方法执行 -->
                            Object res = method.invoke(obj,args);
                            <!-- 方法执行之后的增强部分 -->
                            return res;
                        }
                    })
                [5]外部调用JDKProxy(实现类的实例)方法
    (三)AOP术语
        (一)四个术语
            1.连接点:类中可以被增强的方法被称为连接点
            2.切入点:类中被实际增强的方法被称为切入点
                <!-- 切入点表达式 -->
                    execution([权限修饰符][返回类型][包.类名称][方法名称]([参数列表]))
            3.通知(增强):实际增强的逻辑部分被称为通知
                (1)前置通知
                (2)后置通知
                (3)环绕通知
                (4)异常通知
                (5)最终通知
            4.切面:是动作，指把通知应用到切入点的过程
    (四)AOP的使用
        (一)准备：
            1.AspectJ框架
                (1)介绍：Spring框架一般基于AspectJ实现AOP操作
                    <!-- AspectJ不是Spring组成部分，是独立的AOP框架，只是一般和Spring组合使用，进行AOP操作 -->
                (2)使用方式：
                    [1]基于xml配置文件实现
                    [2]基于注解方式实现
                (3)使用：
                    *1*.注解方式
                        <!-- 引入jar包依赖 -->
                        [1]引入spring-aspects的jar包
                        [2]引入com.springsource.net.sf.cglib的jar包
                        [3]引入com.springsource.org.aopalliance的jar包
                        [4]引入com.springsource.org.aspectj.weaver的jar包
                        [5]创建UserDao接口和其实现类UserDaoImpl，并在实现类里实现方法
                        [6]创建增强类UserProxy，在此类里创建方法，让不同方法代表不同的通知类型(前置通知等)
                        [7]进行通知的配置
                            {1}在spring配置文件中，开启注解扫描(也可创建配置类)-----<!--详见第五大节的第(一)小节的第{1}小节的第2.小节中的第(2)小节-->
                                此外还需在xml文件的beans标签中添加属性
                                    xmlns:aop="http://www/springframework.org.schema.aop"
                                在xsi:schemaLocation属性里添加
                                    http://www.springframework.org/schema/aop
                                    http://www.springframework.org/schema/aop/spring-aop.xsd
                            {2}使用注解创建User和UserProxy对象
                                User使用@Repository
                                UserProxy使用@Component
                            {3}在增强的类UserProxy上面添加注解@Aspect
                            {4}在spring配置文件中开启Aspect生成代理对象
                                在xml文件中beans标签中声明
                                    <!-- 在完全注解开发中，可通过创建AOP配置类，并用  
                                        @Configuration
                                        @ComponentScan(basePackages={})
                                        @EnableAspectJAutoProxy(proxyTargetClass=true)替代注解扫描配置和下面的aop:aspectj-autoproxy配置-->
                                    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
                            {5}配置不同类型的通知
                                *1*.在增强类中，在作为通知方法的上面添加通知类型注解，使用切入点表达式
                                    <!--前置通知  -->
                                        @Before(value="execution(* com.atguigu.spring5.aopanno.User.add(..))")
                                        public void before(){};
                                    <!--环绕通知的特别点  -->
                                        @Around(value="execution(* com.atguigu.spring5.aopanno.User.add(..))")
                                        public void around(ProceedingJoinPoint proceedingJoinPoint){
                                            System.out.println("环绕之前");
                                            <!-- 调用被增强的方法的逻辑 -->
                                            proceedingJoinPoint.proceed();
                                            System.out.println();
                                        }
                                    <!-- 异常通知只有出现异常才会出现 -->
                                    <!--备注-->
                                    通知类型注解的类型
                                        <!--前置通知  -->
                                        [{1}]@Before(value = "切入点表达式")
                                        <!--后置通知，出现异常时不执行  -->
                                        [{2}]@AfterReturning(value = "切入点表达式")
                                        <!-- 最终通知，出现异常时仍执行 -->
                                        [{3}]@After(value = "切入点表达式")
                                        <!-- 异常通知 -->
                                        [{4}]@AfterThrowing(value = "切入点表达式")
                                        <!-- 环绕通知 -->
                                        [{5}]@Around(value = "切入点表达式")
                                    切入点表达式
                                        eg:
                                            [{1}]对com.atguigu.dao.BookDao类里的add()方法进行增强
                                                <!-- 星号表示通用权限，返回类型可省略,星号后有空格-->
                                                [1]execution(* com.atguigu.dao.BookDao.add(..))
                                            [{2}]对com.atguigu.dao.BookDao类里的所有方法进行增强
                                                [1]execution(* com.atguigu.dao.BookDao.*(..))
                                            [{3}]对com.atguigu.dao包里的所有类的所有方法进行增强
                                                [1]execution(* com.atguigu.dao.*.*(..))
                        [8]相同切入点的抽取
                            {1}创建一个方法(比如pointdemo)
                            {2}使用@Pointcut(value = "共同的切入点表达式")
                                eg:
                                    @Pointcut(value="execution(* com.atguigu.spring5.aopanno.User.add(..))")
                                    public void pointdemo(){};
                            {3}使用相同切入点
                                @Before(value = "pointdemo()")
                                        public void before(){};
                        [9]一个方法有多个增强类，设置增强类的优先级
                            在增强类上添加@Order(数字类型值)，数字的值越小，优先级越高
                    *2*.配置文件方式
                         <!-- 引入jar包依赖 -->
                        [1]引入spring-aspects的jar包
                        [2]引入com.springsource.net.sf.cglib的jar包
                        [3]引入com.springsource.org.aopalliance的jar包
                        [4]引入com.springsource.org.aspectj.weaver的jar包
                        [5]创建UserDao接口和其实现类UserDaoImpl，并在实现类里实现方法
                        [6]创建增强类UserProxy，在此类里创建方法，让不同方法代表不同的通知类型(前置通知等)
                        [7]进行通知的配置
                            {1}在spring配置文件xml文件中的beans标签中添加属性
                                    xmlns:aop="http://www/springframework.org.schema.aop"
                                在xsi:schemaLocation属性里添加
                                    http://www.springframework.org/schema/aop
                                    http://www.springframework.org/schema/aop/spring-aop.xsd
                        [8]在xml文件中beans标签里添加bean标签，创建增强类和被增强类的对象
                            <bean id="userProxy" class="com.atguigu.spring5.aopxml.UserProxy">
                            <bean id="user" class="com.atguigu.spring5.aopxml.User">
                        [9]在xml文件中beans标签里添加aop:config标签，配置aop增强
                            <aop:config>
                                <!-- 配置切入点 -->
                                <aop:pointcut id="切入点名字(可自定义)" expression="(* com.atguigu.spring5.aopxml.User.add(..))">
                                <!-- 配置切面 -->
                                <aop:aspect ref="userProxy">
                                    <!-- 前置通知 -->
                                    <aop:before method="增强类中对应的方法的名称" pointcut-ref="切入点名字"/>
                                </aop:aspect>
                            </aop:config>

四、Spring的特点
    1.方便解耦，简化开发
    2.Aop编程的支持
    3.方便程序测试
    4.方便和其他框架进行整合
    5.降低JavaEE API的使用难度
    6.方便进行事务操作

五、Spring的使用
    (一)IOC操作Bean管理
        {1}创建对象
            1.配置文件方式(XML)
                (1)前提：需要无参构造器
                (2)在XML文件中添加
                    <bean id="user" class="xxx.xxx.User"></bean>
                (3)在需要的位置获取对象
                    [1]获取spring配置文件
                        <!-- 配置文件在src文件下 -->
                        ApplicationContext context = new ClassPathXmlApplicationContext("文件名.xml");
                        <!-- 配置文件在别的路径里 -->
                        ApplicationContext context = new FileSystemXmlApplicationContext("绝对路径+文件名.xml");
                    [2]使用getBean方法获取创建对象  
                        User user = context.getBean("user",User.class);
            2.注解方式
                (1)注解的种类
                    [1]注入实例(对象)的注解(配合组件扫描)
                        {1}@Component(通用注解，可以用于创建bean实例)
                        {2}@Service(用在Service层的注解，可以用于创建bean实例)
                        {3}@Controller(用在Controller层的注解，可以用于创建bean实例)
                        {4}@Repository(用在Dao层的注解，可以用于创建bean实例)
                    [2]属性注入的注解
                        {1}@Autowired：根据属性数据类型自动注入(自动装配)
                        {2}@Qualifier：根据属性名称进行注入
                        {3}@Resource：可以根据属性类型注入，也可以根据属性名称注入
                        {4}@Value：注入普通类型属性
                (2)注入实例(对象)的注解的使用
                    [1]相对于xml配置文件方式，注解方式需要额外添加spring-aop的jar包
                    [2]开启组件扫描
                        {1}往xml文件中的beans标签里添加属性
                                xmlns:context="http://www.springframework.org/schema/context"
                            在xsi：schemaLocation属性里添加
                                http://www.springframework.org/schema/context http://www.springframework.orgschema/context/spring-context.xsd
                        {2}往beans标签里添加context:component-scan标签
                            [1]<!-- 第一种写法，多个包之间使用逗号隔开-->
                                <context-component-scan base-package="xxx.xxx.xxx.包名1,xxx.xxx.xxxx.包名2"></context-component-scan>
                            [2]<!-- 第二种写法，填写多个包共同的上级目录 -->
                                <context-component-scan base-package="xxx.xxx.xxx.包名3"></context-component-scan>
                            [3]
                                <1><!-- 自己配置filter组件扫描 -->
                                    <context-component-scan base-package="xxx.xxx.xxx.包名4" use-default-filter="false">
                                        <!--配置只扫描@Controller注解的类-->
                                        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller">
                                    </context-component-scan>
                                <2><!--  使用默认filter组件扫描，不配置filter组件扫描-->
                                    <context-component-scan base-package="xxx.xxx.xxx.包名4">
                                        <!--不扫描@Controller注解的类-->
                                        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller">
                                    </context-component-scan>
                        {3}创建类，在类上面添加注解
                            @Service(value="")  <!--value相当于<bean id="" class=""> 里的id的值，如果不填写value的值，则默认是类名，且首字母自动小写-->
                            public class UserService
                        {4}在需要的位置获取对象
                            [1]获取spring配置文件
                                <!-- 配置文件在src文件下 -->
                                ApplicationContext context = new ClassPathXmlApplicationContext("文件名.xml");
                                <!-- 配置文件在别的路径里 -->
                                ApplicationContext context = new FileSystemXmlApplicationContext("绝对路径+文件名.xml");
                            [2]使用getBean方法获取创建对象  
                                UserService userService = context.getBean("userService",UserService.class);
                (3)属性注入的注解的使用
                    [1]@Autowired的使用
                        eg:
                            <1>把Service和dao对象创建，在service和dao类添加创建对象注解
                            <2>在service注入dao对象：在service类添加dao类型属性，在属性上使用注解@Autowired(dao不需要set方法，因为set方法已经封装好待使用)
                    [2]@Qualifier的使用
                        <!-- 此注解要和@Autowired一起使用 -->
                        eg:
                            <1>把Service和dao对象创建，在service和dao类添加创建对象注解
                            <2>在service注入dao对象：在service类添加dao类型属性，在属性上使用注解@Autowired(dao不需要set方法，因为set方法已经封装好待使用)和@Qualifier(value="属性名")<!--此处属性名为创建bean实例的注解中的value值，按照属性名匹配注入-->
                    [3]@Resource
                        eg:
                            <1>把Service和dao对象创建，在service和dao类添加创建对象注解
                            <2>在service注入dao对象：在service类添加dao类型属性，在属性上使用注解@Resource(name = "属性名")<!---此处属性名为创建bean实例的注解中的value值，按照属性名匹配注入--><!--如果不使用name值标注，则根据属性类型自动注入-->
                    [4]@Value
                        eg:
                            <1>声明一个普通变量(String类型)
                            <2>使用@Value(value = "abc")标注在此变量上，则视为String xxx = "abc";
                (4)纯注解开发(不使用xml文件配置开启组件扫描)
                    [1]创建配置类替代xml配置文件(一般叫做xxxconfig,放在config包下)
                    <!-- 开启组件扫描 -->
                    [2]用@Configuration注解标注此配置类
                    [3]用ComponentScan(basePackages={"xxx.xxxx.xxx包名"})注解标注此配置类
                    <!-- 在需要的地方获取bean实例 -->
                    [4]
                        ApplicationContext context = new AnnotationConfigApplicationContext(配置类名.class);
                        UserService userservice = context.getBean("userService",UserService.class);
        {2}注入属性
            1.DI：依赖注入，就是注入属性
                (1)调用实体类的set方法进行注入(需要在实体类内声明对应的set方法)
                    [1]往xml里对应相应实体的bean标签里添加property标签，name值填写属性名，value则是填写属性值，如此便可在Spring创建对象后直接注入属性
                        <bean id="user" class="xxx.xxx.User">
                            <property name="" value=""></property>
                        </bean>
                    [2]使用p名称空间注入，可以简化基于xml配置方式，但底层仍然是使用set方法注入
                        *1.往beans标签里添加xmlns:p="http://www.springframework.org/schema/p"
                        *2.往bean标签里添加属性p:属性名="属性值"
                         <bean id="user" class="xxx.xxx.User" p:属性名="属性值">
                        </bean>
                (2)调用有参构造器进行注入
                    [1]往xml里对应相应实体的bean标签里添加constructor-arg标签，name值填写属性名，value则是填写属性值，如此便可在Spring创建对象时直接注入属性
                        <bean id="user" class="xxx.xxx.User">
                            <constructor-arg name="" value=""></constructor-arg>
                            <!-- 也可使用index替代name属性，index的值代表构造器中第几个参数 -->
                            <constructor-arg index="" value=""></constructor-arg>
                        </bean>
                (3)xml注入其他类型属性
                    [1]字面量
                        *1.注入null值:不声明value属性，在property标签内声明null标签
                            <bean id="user" class="xxx.xxx.User">
                                <property name="">
                                    <null/>
                                </property>
                            </bean>
                        *2.注入的属性值包含特殊符号
                            <1>把特殊符号进行转义
                            <2>把带特殊符号内容写到CDATA
                                <bean id="user" class="xxx.xxx.User">
                                    <property name="">
                                        <value>
                                            <![CDATA[属性值]]>
                                        </value>
                                    </property>
                                </bean>
                    [2]注入属性-外部bean(MVC层之间的类注入)
                        eg:在service层里注入dao层:
                            name为在service里声明的外部bean的属性名，ref为要注入的外部bean的id值
                                <bean id="userService" class="xxx.xxx.userService">
                                    <property name="属性名" ref="userDao"></property>
                                </bean>
                                <bean id="userDao" class="xxx.xxx.userDao"></bean>
                    [3]注入属性-内部bean(往实体类里注入另一个实体类的对象)
                    和
                    级联赋值(往实体类里注入另一个实体类时，给注入的实体类的属性赋值)
                        *1.一对多关系
                            <!-- 此写法也实现了级联赋值 -->
                            <bean id="emp" class="xxx.xx.xx.Emp">
                                <!-- 此处的最外部的name值为Emp类里声明的Dept对象的对象名 -->
                                <property name="dept">
                                    <bean id="dept" class="xxx.xxx.xx.Dept">
                                        <property name="内部bean的属性名" value="内部bean的属性值">
                                    </bean>
                                </property>
                            </bean>
                        *2.级联赋值
                            <!-- 级联赋值的另一种写法,可以采取与外部bean同样的写法 -->
                                <bean id="emp" class="xxx.xx.xx.Emp">
                                    <property name="dept" ref="dept"></property>
                                </bean>
                                <bean id="dept" class="xxx.xxx.xx.Dept">
                                    <property name="内部bean的属性名" value="内部bean的属性值">
                                </bean>
                            <!-- 也可在被注入的实体类的bean标签里添加property标签，但此写法需要在被注入的实体类内声明注入实体类的get方法-->
                                <bean id="emp" class="xxx.xx.xx.Emp">
                                    <property name="dept1" ref="dept"></property>
                                    <property name="dept1.内部bean的属性名" value="内部bean的属性值">
                                </bean>
                                <bean id="dept" class="xxx.xxx.xx.Dept"></bean>
                    [4]注入其他类型属性
                        *1.数组类型属性
                            <bean id="stu" class="xxx.xxx.xx.Stu">
                                <property name="属性名">
                                    <array>
                                        <value>数组值</value>
                                        <value>数组值</value>
                                        .....
                                        <value>数组值</value>
                                    </array>
                                </property>
                            </bean>
                        *2.注入List集合类型属性
                            <bean id="stu" class="xxx.xxx.xx.Stu">
                                <property name="属性名">
                                    <list>
                                        <value>集合值</value>
                                        <value>集合值</value>
                                        .....
                                        <value>集合值</value>
                                    </list>
                                </property>
                            </bean>
                        *3.注入Map集合类型属性
                            <bean id="stu" class="xxx.xxx.xx.Stu">
                                <property name="属性名">
                                    <map>
                                        <entry key="" value="">
                                        <entry key="" value="">
                                        ....
                                        <entry key="" value="">
                                    </map>
                                </property>
                            </bean>
                        *4.注入Set集合类型属性
                            <bean id="stu" class="xxx.xxx.xx.Stu">
                                <property name="属性名">
                                    <set>
                                        <value>集合值</value>
                                        <value>集合值</value>
                                        .....
                                        <value>集合值</value>
                                    </set>
                                </property>
                            </bean>
                        *5.注入对象类型值给集合
                            <1>List集合
                                <bean id="stu" class="xxx.xxx.xx.Stu">
                                    <property name="属性名">
                                        <list>
                                            <ref bean="course1"></ref>
                                            <ref bean="course2"></ref>
                                        </list>
                                    </property>
                                </bean>
                                <bean id="course1" class="xxx.xx.xxxx.Course">
                                    <property name="" value="">
                                </bean>
                                <bean id="course2" class="xxx.xx.xxxx.Course">
                                    <property name="" value="">
                                </bean>
                        *6.提取集合注入的部分
                            <1>在spring配置文件中引入命名空间util:
                                在beans标签里声明属性
                                    xmlns:util="http://www.springframework.org/schema/util"
                                在xsi：schemaLocation属性里添加
                                    http://www.springframework.org/schema/util http://www.springframework.orgschema/util/spring-util.xsd
                            <2>使用提供的工具标签
                                eg:List集合
                                    <util:list id="List">
                                        <value>集合值</value>
                                        <value>集合值</value>
                                        .....
                                        <value>集合值</value>
                                    </util:list>
                                    <bean id="book" class="xxx.xxx.xxx.Book">
                                        <property name="list" ref="List"></property>
                                    </bean>
    (二)JdbcTemplate的使用
        1.引入jar包
            (1)spring-jdbc的jar包
            (2)spring-tx的jar包(事务)
            (3)spring-orm的jar包
            (4)数据库连接的jar包mysql-connector-java
            (5)连接池jar包(比如德鲁伊druid)
        2.配置数据库连接池
            详见第六大节的第(五)小节的第1小节
        3.配置JdbcTemplate对象，注入DataSource
            在xml文件里的beans标签中添加bean标签
                <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
                    <!-- 将数据库的bean注入 -->
                    <property name="dataSource" ref="数据库注入的bean的id值"></property>
                </bean>
        4.创建Service类和dao类，在dao类注入JdbcTemplate对象
            在dao的实现类中声明private JdbcTemplate jdbcTemplate，并用注解@AutoWired标注
                @Autowired
                private JdbcTemplate jdbcTemplate
        5.使用JdbcTemplate模板操作数据库
            (1)创建实体类pojo
            (2)编写Service和Dao层
                [1]Dao层:在实体类调用JdbcTemplate
                    {1}JdbcTemplate的方法：
                        <!--添加、修改、删除操作  -->
                        *1*.update(String sql,Object... args)
                            可以实现添加、修改、删除操作，第一个参数为sql语句；第二个为sql语句中的占位符对应的参数
                        <!-- 查询操作 -->
                        *2*.queryForObject(String sql,Class<T> requiredType)
                            可以实现查询数值操作，第一个参数是sql语句；第二个为返回类型的Class
                        *3*.queryForObject(String sql,RowMapper<T> rowMapper,Object... args)
                            可以实现查询返回对象操作，第一个参数是sql语句；第二个参数为借口，返回不同类型数据，会使用这个借口里面的实现类完成数据的封装；第三个参数为sql语句中的占位符对应的参数
                                eg:
                                    实体类名 实例名 = jdbcTemplate.queryForObject(sql,newBeanPropertyRowMapper<实体类名>(实体类名.class),id);
                        *4*.query(String sql,RowMapper<T> rowMapper,Object... args)
                            可以查询返回集合(List集合)操作，第一个参数是sql语句；第二个参数为借口，返回不同类型数据，会使用这个借口里面的实现类完成数据的封装；第三个参数为sql语句中的占位符对应的参数
                                eg:
                                    List<实体类名> list = jdbcTemplate.queryForObject(sql,newBeanPropertyRowMapper<实体类名>(实体类名.class),id);
                        <!-- 批量操作 -->
                        *5*.batchUpdate(String sql,List<Object[]> batchArgs)
                            第一个参数是sql语句；第二个参数是List集合，添加多条记录
            (3)调用Service层的对应的方法
    (三)事务
        1.事务的特性
        2.spring中事务的引入(一般添加在JavaEE三层结构里的Service层(业务层))
            两种方式
                (1)编程式事务管理
                (2)声明式事务管理(底层使用AOP)
                    [1]注解方式
                        {1}引入数据库
                        {2}在spring配置文件配置事务管理器
                            在beans标签内添加
                                <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                                    <!-- 注入数据源,引用数据库dataSource的bean -->
                                    <property name="dataSource" ref="dataSource">
                                </bean>
                        {3}在配置文件中开启事务注解
                            在xml配置文件引入名称空间tx
                                在beans标签内引入属性
                                    xmlns:tx="http://www.springframework.org/schema/tx"
                                在xsi:schemaLocation属性内添加
                                    http://www.springframework.org/schema/tx
                                    http://www.springframework.org/schema/tx/spring-tx.xsd
                        {4}开启事务注解:
                            beans标签内添加标签
                                <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
                        {5}在service类上面添加事务注解@Transactional(或者添加在service类里的方法上面)
                    [2]xml配置文件方式
                        {1}引入数据库
                        {2}在spring配置文件配置事务管理器
                            在beans标签内添加
                                <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                                    <!-- 注入数据源,引用数据库dataSource的bean -->
                                    <property name="dataSource" ref="dataSource">
                                </bean>
                        {3}配置通知
                            *1*.在xml配置文件引入名称空间tx
                                在beans标签内引入属性
                                    xmlns:tx="http://www.springframework.org/schema/tx"
                                在xsi:schemaLocation属性内添加
                                    http://www.springframework.org/schema/tx
                                    http://www.springframework.org/schema/tx/spring-tx.xsd
                            *2*.在beans标签内添加
                                <tx:advice id="txadvice">
                                    <!-- 配置事务参数 -->
                                    <tx:attributes>
                                        <!-- 指定哪种规则的方法上面添加事务 -->
                                        <tx:method name="方法名" isolution="">
                                        <tx:method name="">
                                    </tx:attributes>
                                </tx:advice>
                        {4}配置切入点和切面
                            *1*.在xml配置文件引入名称空间tx
                                在beans标签内引入属性
                                    xmlns:tx="http://www.springframework.org/schema/aop"
                                在xsi:schemaLocation属性内添加
                                    http://www.springframework.org/schema/aop
                                    http://www.springframework.org/schema/aop/spring-aop.xsd
                            *2*.在beans标签内添加标签
                                <aop:config>
                                    <!-- 配置切入点 -->
                                    <aop:pointcut id="pt" expression="execution(* com.atguigu.spring5.service.UserService.*(..))">
                                    <!-- 配置切面,把事务的通知配置到方法上-->
                                    <aop:advisor advice-ref="txadvice" pointcut-ref="pt">
                                </aop:config>
                    [3]完全注解方式
                        {1}创建配置类，使用配置类替代xml配置文件
                            创建一个类，在此类上添加注解
                                @Configuration   <!--代表此类为配置类-->
                                @ComponentScan(basepackage="") <!--包的扫描路径-->
                                @EnableTransactionManagement <!--开启事务-->
                                public class TxConfig{
                                    <!-- 创建数据库连接池 -->
                                    @Bean
                                    public DruidDataSource getDruidDataSource(){
                                        DruidDataSource dataSource = new DruidDataSourced();
                                        dataSource.setDriverClassName("");
                                        dataSource.setUrl("");
                                        dataSource.setPassword("");
                                        return dataSource;
                                    }
                                    <!-- 创建jdbcTemplate对象 -->
                                    @Bean
                                    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
                                        <!-- 方法形参给出类型，IOC自动根据类型从容器中取出并注入 -->
                                        <!-- 注入DataSource -->
                                        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                                        return jdbcTemplate;
                                    }
                                    <!-- 创建事务管理器 -->
                                    @Bean
                                    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
                                        <!-- 方法形参给出类型，IOC自动根据类型从容器中取出并注入 -->
                                        <!-- 创建事务管理器对象 -->
                                        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
                                        <!-- 注入DataSource -->
                                        transactionManager.setDataSource(dataSource)
                                        return transactionManager;
                                    }
                                }
                        {2}在需要的地方读取bean
                            ApplicationContext context = new AnnotationConfigApplicationContext(Txconfig.class);
                            UserService userService = context.getBean("userService",Uservice.class);

        3.Spring事务管理的API
            (1)事务管理器PlatformTransactionManager接口，这个接口针对不同的框架提供不同的实现类
        4.事务传播行为
            当一个事务方法被另外一个事务方法调用时
            (1)七种事务传播行为
                [1]REQUIRED:如果有事务在运行，当前的方法就在这个事务内运行，否则就启动一个新的事务，并在此事务内运行
                    [1](../spring_pic/%E4%BA%8B%E5%8A%A1%E7%9A%84%E4%BC%A0%E6%92%AD-REQUIRED.PNG)
                [2]REQUIRED_NEW:当前的方法必须启动新事务，并在它自己的事务内运行。如果有事务正在运行，应该将它挂起
                    [1](../spring_pic/%E4%BA%8B%E5%8A%A1%E7%9A%84%E4%BC%A0%E6%92%AD-REQUIRED_NEW.PNG)
                [3]SUPPORTS:如果有事务在运行，当前的方法就在这个事务内运行，否则它可以不运行在事务中
            (2)设置事务传播行为
                [1]注解方式
                    在注解@Transactional括号里添加propagation=
                        eg:@Transactional(propagation=Propagation.REQUIRED)
        5.事务的隔离级别(解决多事务操作之间产生的问题)
            1.读取的问题：
                [1]脏读：一个未提交的事务读取到另一个未提交的事务的数据
                [2]不可重复读：一个未提交的事务读取到另一提交事务修改数据
                [3]虚(幻)读：一个未提交的事务读取到另一个提交事务添加数据
            2.事务的隔离级别：
                [1](../spring_pic/%E4%BA%8B%E5%8A%A1%E7%9A%84%E9%9A%94%E7%A6%BB%E7%BA%A7%E5%88%AB.PNG)
            3.添加事务的隔离级别(mysql不手动设置隔离级别的情况下，默认级别为REPEATABLE_READ)
                [1]注解方式：
                    在注解@Transactional括号里添加isolation=
                        eg:@Transactional(isolation=Isolation.REPEATABLE_READ)
        6.事务的超时时间
            [1]事务需要在一定时间内进行提交，如果不提交，进行回滚
            [2]Spring里事务的超时时间是-1，表示没有超时时间
            [3]设置超时时间：
                设置时间以秒为单位
                    {1}注解方式：@Transactional(timeout = -1)
        7.事务的是否只读
            @Transactional(readOnly = false)
            默认为false，设置为true时添加、修改和删除操作被禁用，只能查询
        8.事务的回滚
            @Transactional(rollbackFor = false)
            设置哪些异常进行事务回滚
        9.事务的不回滚
            @Transactional(noRollbackFor = false)
            设置出现哪些异常不进行事务回滚

六、bean
    (一)Spring中的两种bean
        1.普通bean
            特点：配置文件中加载的class类型，就是返回的类型
        2.工厂bean---FactoryBean
            (1)特点：配置文件中加载的class类型可以和返回的类型不一样
            (2)使用：
                [1]创建类作为工厂bean，实现接口FactoryBean(需要在FactoryBean后加上想返回的类型的泛型<Course>)
                [2]实现接口里的方法，在实现的方法中定义返回的类型
                    {1}Object getObject():获取实例的方法，可用于定义返回的类型,定义返回类型时此方法的类型需要与继承接口后定义的泛型一致
                    {2}Class<?> getObjectType():获取返回类型的方法
    (二)bean的作用域
        1.Spring里，默认情况下bean是单实例对象
        2.设置bean实例是单实例还是多实例
            scope属性设置bean是单实例还是多实例
            scope的默认值singleton，表示单例对象；prototype，表示多实例对象(使用prototype属性时，加载spring配置文件时不会创建对象，而是在调用getBean()方法时创建对象)
            <bean id="" class="" scope="singleton"\scope="prototype"></bean>
    (三)bean的生命周期
        <!-- 没有添加bean的后置处理，bean的生命周期有五步 -->
        1.调用无参构造创建bean实例(无参构造)
        2.为bean的属性赋值和对其他bean引用(调用set方法)
        3.调用bean的初始化的方法(需要进行配置，在实体类内)
            <!-- init-method -->
            <bean id="" class="" init-method="">
                <property name="" value=""></property>    
            </bean>
        4.bean可以使用了(获取对象)
        5.当容器关闭时，调用bean的销毁方法(需要自行配置销毁的方法，在实体类内)
            <!-- destroy-method -->
            <bean id="" class="" init-method="" destroy-method="">
                <property name="" value=""></property>
            </bean>
            <!-- 获取spring配置文件-->
            ApplicationContext context = new ClassPathXmlApplicationContext("文件名.xml");
            <!--关闭时调用销毁方法  -->
            ((ClassPathXmlApplicationContext)context).close;
        <!-- ================================================================== -->
        <!-- 添加后置处理器 -->
            1.创建类，实现接口BeanPostProcessor，创建后置处理器
            2.重写
                Object postProcessBeforeInitialization(Object bean,String beanName)
                Object postProcessAfterInitialization(Object bean,String beanName)
                方法
            3.在配置文件中添加
                <bean id="后置处理器的类名" class="xxx.xxx.xxx.后置处理器的类名">
        <!-- 如果加入bean的后置处理器，bean的生命周期有七步 -->
            1.调用无参构造创建bean实例(无参构造)
            2.为bean的属性赋值和对其他bean引用(调用set方法)
            3.把bean实例传递给bean后置处理器中的方法
            4.调用bean的初始化的方法(需要进行配置，在实体类内)
                <!-- init-method -->
                <bean id="" class="" init-method="">
                    <property name="" value=""></property>    
                </bean>
            5.把bean实例传递给bean后置处理器中的方法
            6.bean可以使用了(获取对象)
            7.当容器关闭时，调用bean的销毁方法(需要自行配置销毁的方法，在实体类内)
                <!-- destroy-method -->
                <bean id="" class="" init-method="" destroy-method="">
                    <property name="" value=""></property>
                </bean>
                <!-- 获取spring配置文件-->
                ApplicationContext context = new ClassPathXmlApplicationContext("文件名.xml");
                <!--关闭时调用销毁方法  -->
                ((ClassPathXmlApplicationContext)context).close;
    (四)xml的自动装配
        1.理解：根据指定的装配规则(属性名或属性类型)，Spring自动将匹配的属性值进行注入
        2.使用
            autowire属性：
                byName:根据名字自动装配
                byType:根据属性类型自动装配
            <bean id="emp" class="xxxxx.xx.xx.Emp" autowire=""></bean>
            <!-- 被注入的bean -->
            <!-- byName情况下id值要与被注入类对应的属性名一致 -->
            <!-- byType情况下，不能定义多个同类型的bean，否则报错 -->
            <bean id="dept" class="xxxxx.xx.xx.Dept"></bean>
    (五)引入外部的属性文件
        1.用于配置数据库信息
            (1)配置连接池
                [1]导入jar包
                [2]创建外部properties文件
                [3]xml中配置连接池
                    {1}往xml文件中的beans标签里添加属性
                            xmlns:context="http://www.springframework.org/schema/context"
                        在xsi：schemaLocation属性里添加
                            http://www.springframework.org/schema/context http://www.springframework.orgschema/context/spring-context.xsd
                    {2}在beans标签内引入外部属性文件
                        <context:property-placeholder location="classpath:xxxx.properties"/>
                        <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
                            <property name="driverClassName" value="${prop.driverClass}">
                            <property name="url" value="${prop.url}">
                            <property name="username" value="${prop.userName}">
                            <property name="password" value="${prop.password}">
                        </bean>
                    <!-- 普通xml方式配置德鲁伊连接池 -->
                    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
                        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver">
                        <property name="url" value="jdbc:mysql://localhost:3306/数据库名">
                        <property name="username" value="root">
                        <property name="password" value="password">
                    </bean>

七、JdbcTemplate
    (一)介绍：Spring框架对JDBC进行的封装，是一套模板
    (二)使用：详见第五大节第(二)小节：JdbcTemplate的使用

八、Spring5的新功能
    (一)整个Spring5框架的代码基于Java8，运行时兼容JDK9，许多不建议使用的类和方法在代码库中删除
    (二)新功能：
        1.自带通用的日志封装：
            (1)Spring5已经移除Log4jConfigListener，官方建议使用Log4j2
            (2)Spring5框架整合Log4j2
                [1]引入jar包
                    log4j-api
                    log4j-core
                    log4j-slf4j-impl
                    slf4j-api
                [2]创建log4j2.xml(文件名固定，不可自定义)
                    填写内容：
                        [1](./spring_pic/log4j2xml%E6%96%87%E4%BB%B6%E9%85%8D%E7%BD%AE.PNG)
                        <?xml version="1.0" encoding="UTF-8"?>
                        <configuration status="INFO">
                            <addpenders>
                                <console name="Console" target="SYSTEM_OUT">
                                    <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
                                </console>
                            </appenders>
                            <loggers>
                                <root level="info">
                                    <appender-ref ref="Console"/>
                                </root>
                            </loggers>
                        </configuration>
                [3]可以手动获取日志
                    private static final Logger log = LoggerFactory.getLogger(声明此变量的类名.class);
        2.@Nullable注解
            (1)可以使用在：
                [1]属性：表示属性值可以为空
                [2]方法：表示返回值可以为空
                [3]参数上面：表示参数值可以为空
            (2)作用：表示值可以为空
        3.Spring5核心容器支持函数式风格GenericApplicationContext
            [1](./spring_pic/Spring5%E6%A0%B8%E5%BF%83%E5%AE%B9%E5%99%A8%E6%94%AF%E6%8C%81%E5%87%BD%E6%95%B0%E5%BC%8F%E9%A3%8E%E6%A0%BCGenericApplicationContext.PNG)
        4.Spring5整合Junit5测试框架
            (1)整合Junit4
                [1]引入jar包
                    spring-test
                [2]创建测试类，使用注解
                    @Runwith(SpringJunit4ClassRunner.class)指定Junit4测试框架版本
                    @ContextConfiguration("classpath:bean1.xml")指定配置文件路径，加载配置文件
                [3]声明属性加上IOC注入的注解，可以实现自动注入
            (2)整合Junit5
                [1]引入jar包
                    spring-test
                <!-- 第2步两个注解可以用
                    @SpringJUnitConfig(locations = "classpath:bean1.xml")
                    替代 -->
                [2]创建测试类，使用注解
                    @ExtendWith(SpringExtension.class)指定Junit5测试框架版本
                    @ContextConfiguration("classpath:bean1.xml")指定配置文件路径，加载配置文件
                [3]声明属性加上IOC注入的注解，可以实现自动注入
                [4]测试方法上的注解@Test需要选择Junit5版本的
        5.SpringWebFlux
            (1)介绍：
                    [1]Spring5添加的模块，用于Web开发，功能和SpringMVC类似，Webflux是使用当前一种比较流行的响应式编程而出现的框架
                    [2]传统的web框架，比如SpringMVC，基于Servlet容器；Webflux是一种异步非阻塞的框架，异步非阻塞的框架在Servlet3.1以后才支持，Webflux框架核心是基于Reactor的相关API实现的
                    [3]什么是异步非阻塞：[1](./spring_pic//%E4%BB%80%E4%B9%88%E6%98%AF%E5%BC%82%E6%AD%A5%E9%9D%9E%E9%98%BB%E5%A1%9E.PNG)
            (2)特点：
                [1]非阻塞式：在优先的资源下，提高系统的吞吐量和伸缩性，以Reactor为基础实现响应式编程
                [2]函数式编程：Spring5基于Java8，Wenflux使用Java8函数式编程方式实现路由请求
            (3)和SpringMVC比较
                [1](./spring_pic/SpringMVC%E5%92%8CSpringWebflux.PNG)
                相同点：
                    [1]都可以使用注解方式
                    [2]都运行在Tomcat等容器中
                不同点
                    [1]WebFlux使用异步响应式编程方式，SpringMVC使用命令式编程方式
                如何选择SpringMVC和WebFlux：在同时接受多请求时，可以选择WebFlux提高吞吐量
            (4)响应式编程
                [1]介绍：[1](./spring_pic/%E4%BB%80%E4%B9%88%E6%98%AF%E5%93%8D%E5%BA%94%E5%BC%8F%E7%BC%96%E7%A8%8B.PNG)
                [2]Java8及之前版本提供了观察者模式两个类Observer和Observable，监控数据变化
                    {1}使用：
                        *1*.创建一个类继承Observable
                        *2*.在此类里的main方法里创建自身的对象
                        *3*.调用addObserver()添加观察者
                        *4*.调用setChanged()数据变化
                        *5*.调用notifyObservers()通知
                [3]Java9由flow类取代了Observer和Observable两个类
                [4]用Reactor实现响应式编程
                    {1}响应式编程操作中，Reactor满足Reactive规范框架
                    {2}Reactor的两个核心类:Mono和Flux，这两个类实现接口Publish，提供丰富的操作符
                        *1*.Flux对象实现发布者，返回N个元素；Mono实现发布者，返回0或1个元素
                        *2*.Flux和Mono都是数据流的发布者，使用了Flux和Mono都可以发出三种数据信号：
                            元素值
                            错误信号(终止信号，用于告诉订阅者数据流结束了，同时把错误信息传递给订阅者)
                            完成信号(终止信号，用于告诉订阅者数据流结束了)
                            <!--  -->
                            三种信号的特点：
                                错误信号和完成信号都是终止信号，无法共存
                                如果没有发送任何元素值，而是直接发送错误或完成信号，表示是空数据流
                                如果没有错误信号，没有完成信号，表示是无限数据流    
                        *3*.代码实现：
                            <1>在maven中引入依赖
                                <dependency>
                                    <groupId>io.projectreactor</groupId>
                                    <artifactId>reactor-core</artifactId>
                                    <version>3.1.5.RELEASE</version>
                                </denpendency>
                            <2>创建一个类并声明main方法
                            <3>just方法声明
                                调用Flux.just(1,2,3,4);
                                调用Mono.just(1);
                            <4>其他的方法
                                Interger[] array = {1,2,3,4};
                                Flux.fromArray(array);
                                <!--  -->
                                List<Integer> list = Arrays.asList(array);
                                Flux.fromterable(list);
                                <!--  -->
                                Stream<Integer> stream = list.stream();
                                Flux.fromStream(stream)
                            <!-- 第<2><3>步的方法调用只是声明数据流，数据流并没有发出，只有进行订阅后才会发出数据流 -->
                        *4*.操作符<!-- 对数据流进行一道道操作，成为操作符，比如工厂流水线 -->
                            <1>map元素映射为新元素
                                [1](./spring_pic/Reactor%E6%93%8D%E4%BD%9C%E7%AC%A6-map%E5%85%83%E7%B4%A0%E6%98%A0%E5%B0%84%E4%B8%BA%E6%96%B0%E5%85%83%E7%B4%A0.PNG)
                            <2>flatMap元素映射为流，将每个元素转换成流并合并成一个大的流
                                [1](./spring_pic/Reactor%E6%93%8D%E4%BD%9C%E7%AC%A6-flatmap%E5%B0%86%E5%85%83%E7%B4%A0%E6%98%A0%E5%B0%84%E4%B8%BA%E6%B5%81.PNG)
            (5)WebFlux执行流程和核心API
                [1]SpringWebFlux基于Reactor，默认使用的容器是Netty，Netty是高性能的NIO框架(异步非阻塞框架)
                    {1}Netty
                        <1>BIO
                        <2>NIO非阻塞
                            [1](./spring_pic/NIO-Selector%E6%A8%A1%E5%9E%8B.PNG)
                [2]SpringWebFlux执行过程和SpringMVC相似，SpringWebFlux核心控制器DispatcherHandler，实现了接口WebHandler
                    [1](./spring_pic/SpringWebFlux%E6%A0%B8%E5%BF%83%E6%8E%A7%E5%88%B6%E5%99%A8DispatcherHandler%EF%BC%8C%E5%AE%9E%E7%8E%B0%E4%BA%86%E6%8E%A5%E5%8F%A3WebHandler.PNG)
                [3]SpringWebFlux里面的组件
                    {1}DispatcherHandler:负责请求的处理；
                    {2}HandlerMapping:负责匹配请求需要调用的方法
                    {3}HandlerAdapter:负责请求处理，相当于SpringMVC里的业务方法Service
                    {4}HandlerResultHandler:负责响应结果处理
                [4]SpringWebflux实现函数式编程，两个接口：
                    {1}RouterFunction:路由处理
                    {2}HandlerFunction:处理函数
            (6)SpringWebflux模型
                [1]基于注解编程模型:
                    {1}和SpringMVC相似，在项目组导入依赖即可使用
                    {2}在SpringBoot自动配置相关运行容器，默认情况下使用Netty服务器
                    {3}具体使用
                        <1>创建SpringBoot工程，引入webflux依赖：在maven中将依赖
                            <dependency>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-starter</artifactId>
                            </denpendency>
                            修改成
                            <dependency>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-starter-webflux</artifactId>
                            </denpendency>
                        <2>配置启动的端口号：在配置文件application.properties文件中添加server.port=端口号
                        <3>创建包和相关类(业务层、控制层、模型层)
                        <4>调用API
                [2]基于函数式编程模型
                    {1}使用此方式，需要自己初始化服务器
                    {2}基于函数式编程模型的时候，有两个核心接口：
                        RouterFunction(实现路由功能，将请求转发给对应的Handler)
                        HandlerFunction(处理请求生成响应的函数)
                    {3}具体使用是编写这两个接口的实现类并启动需要的服务器
                    {4}SpringWebflux请求和响应不再是ServletRequest和ServletResponse，而是ServerRequest和ServerResponse
                    {5}具体使用:
                        <1>创建SpringBoot工程，引入webflux依赖：在maven中将依赖
                            <dependency>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-starter</artifactId>
                            </denpendency>
                            修改成
                            <dependency>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-starter-webflux</artifactId>
                            </denpendency>
                        <2>配置启动的端口号：在配置文件application.properties文件中添加server.port=端口号
                        <3>创建包和相关类(业务层、模型层，不包含控制层)
                        <4>创建Handler层，创建Handler类UserHandler，调用业务层
                        <5>
                            创建一个类，声明router路由
                                public RouterFunction<ServerResponse> routerFunction(){
                                    UserService userService = new UserServiceImpl();
                                    UserHandler handler = new UserHandler(userService);
                                    return RouterFunction.route(
                                        GET("路径").and(accept(APPLICATION_JSON)),handler::方法名).addRoute(GET("路径").and(accept(APPLICATION_JSON)),handler::方法名));
                            }
                            创建服务器
                                public void createReactorServer(){
                                    <!-- 路由和handler适配 -->
                                    RouterFunction<ServerResponse> route = routingFunction();
                                    HttpHandler httphandler = toHttpHandler(route);
                                    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httphandler);
                                    <!-- 创建服务器 -->
                                    HttpServer httpServer = HttpServer.create();
                                    httpServer.handle(adapter).bindNow();
                                }
                        <6>最终调用：创建main方法
                            public static void main(String[] args){
                                Server server = new Server();
                                server.createReactorServer();
                                System.out.println("enter to exit");
                                System.in.read();
                            }
                [3]使用WebClient调用，而不是通过浏览器：
                    创建一个类，声明main方法
                        public static void main(String[] args){
                                <!-- 调用服务器地址 -->
                                WebClient webClient = WebClient.create("http://127.0.0.1:5794");
                                <!-- 调用方法 -->
                                User result = webClient.get().uri("/user/{id}",id).accept(MediaType.APPLICATION_JSON).retrieve.bodyToMono(User.class).block();
                            }
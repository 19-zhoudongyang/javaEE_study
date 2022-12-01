[toc]
# 一、SpringMVC简介
   
## (一)导入：什么是MVC：
![1](springMVC_pic/whatisMVC.PNG)

## (二)什么是SpringMVC
![1](springMVC_pic/whatisSpringMVC.PNG)

## (三)SpringMVC的特点
![1](springMVC_pic/CharacteristicOfSpringMVC.PNG)

## (四)快速使用：
配置文件方式：
### 1.创建maven工程

### 2.导入依赖

### 3.在src目录下的main包里创建webapp文件夹，并创建xml配置文件，添加springMVC的配置
![1](springMVC_pic/SpringMVCxmlconfigurationfile.PNG)

### 4.创建前端控制器DispatcherServlet配置文件，并在此文件里开启包扫描和配置视图解析器
在springMVC的xml配置文件里指定其位置(也可不指定，则默认查找路径为WEB-INF文件夹下)
![1](springMVC_pic/DispatcherservletconfigurationfileofspringMVCfront-endcontroller.PNG)

### 5.在WEB-INF下创建视图解析器指定的路径文件夹，并创建html文件，在html标签内添加Thymeleaf的命名空间
![1](springMVC_pic/AddthymeleafnamespaceonHTMLpage.PNG)

### 6.创建请求控制器：处理具体请求
![1](springMVC_pic/Createrequestcontroller_processspecificrequests.PNG)
(1)创建一个Controller控制层类，并用注解@Controller注解标注
(2)编写具体方法(控制器方法)并添加请求映射注解@RequestMapping(value="")，value值为浏览器输入的路径名称

### 7.编写前端页面，浏览器解析绝对路径需要使用Thymeleaf解析
![1](springMVC_pic/%E7%BC%96%E5%86%99%E5%89%8D%E7%AB%AF%E9%A1%B5%E9%9D%A2%EF%BC%8C%E6%B5%8F%E8%A7%88%E5%99%A8%E8%A7%A3%E6%9E%90%E7%BB%9D%E5%AF%B9%E8%B7%AF%E5%BE%84%E9%9C%80%E8%A6%81%E4%BD%BF%E7%94%A8Thymeleaf%E8%A7%A3%E6%9E%90.PNG)

### 8.总结
![1](springMVC_pic/%E5%BF%AB%E9%80%9F%E4%BD%BF%E7%94%A8%E6%80%BB%E7%BB%93.PNG)


注解方式：
# 二、基础功能
    
## (一)注解开发：
### 1.注解：
#### (1)@RequestMapping
##### {1}作用：
![1](springMVC_pic/%40RequestMapping%E6%B3%A8%E8%A7%A3%E7%9A%84%E4%BD%9C%E7%94%A8.PNG)
##### {2}标注位置不同作用不同：
![1](springMVC_pic/%40RequestMapping%E6%A0%87%E6%B3%A8%E4%BD%8D%E7%BD%AE%E4%B8%8D%E5%90%8C%E4%BD%9C%E7%94%A8%E4%B8%8D%E5%90%8C.PNG)

##### {3}属性：
###### <1>value(使用@RequestMapping必须设置此属性)
####### *1.请求路径，可以设置多个值，可以匹配多个请求地址吗，处理多个请求
####### *2.支持ant风格路径：
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7value%E6%94%AF%E6%8C%81ant%E9%A3%8E%E6%A0%BC%E8%B7%AF%E5%BE%84.PNG)
####### *3.支持占位符
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7value%E6%94%AF%E6%8C%81%E5%8D%A0%E4%BD%8D%E7%AC%A6.PNG)
需要使用注解@PathVariable(value="")标注方法形参接收参数，如果不设置属性值，则默认为属性名；请求路径必须包含参数，否则404
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7value%E6%94%AF%E6%8C%81%E5%8D%A0%E4%BD%8D%E7%AC%A62.PNG)

###### <2>method
设置请求方式，除了匹配value值(前提)，还要匹配请求方式，请求才能被控制器方法接收并处理；如果不设置，则表示可以请求所有请求方式
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7method%202.PNG)
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7method.PNG)

###### <3>params
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7params.PNG)

###### <4>headers
![1](springMVC_pic/%40RequestMapping%E5%B1%9E%E6%80%A7headers.PNG)
##### {4}派生注解：
![1](springMVC_pic/%40RequestMapping%E7%9A%84%E6%B4%BE%E7%94%9F%E6%B3%A8%E8%A7%A3.PNG)
@RestController:标注在类上相当于为此类添加了@Controller注解，并为此类的每一个方法标注@ResponseBody注解
##### {5}注意点：
[1]如果不同控制器类中@RequestMapping存在请求地址相同的情况，则报错
            (2)@RequestParam
                详见本章第2.小节获取请求参数的第(2)小节：通过控制器形参自动获取参数第<1>小节
            (3)RequestHeader
                详见本章第2.小节获取请求参数的第(2)小节：通过控制器形参自动获取参数第<2>小节
            (4)@CookieValue
        2.获取请求参数
            <!-- 处理乱码问题(编码不一致)：
                (1)get方式乱码:
                    由tomcat造成，在tomcat文件夹里的conf文件夹里的server.xml配置文件里找到
                        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443"/>
                    添加属性URIEncoding="UTF-8"
                (2)post方式乱码:
                    {1}在过滤器中设置编码，在web.xml中配置过滤器
                        ![1](springMVC_pic/SpringMVC%E8%AE%BE%E7%BD%AE%E8%BF%87%E6%BB%A4%E5%99%A8.PNG)
            -->
            (1)通过ServletAPI获取请求参数
                ![1](springMVC_pic/%E9%80%9A%E8%BF%87ServletAPI%E8%8E%B7%E5%8F%96%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0.PNG)
            (2)通过控制器形参自动获取参数
                ![1](springMVC_pic/%E6%8E%A7%E5%88%B6%E5%99%A8%E6%96%B9%E6%B3%95%E7%9B%B4%E6%8E%A5%E8%8E%B7%E5%8F%96%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0.PNG)
                <!--  -->
                <1>如果形参和请求参数名不一致，则使用注解@RequestParam("请求参数名")标注形参，标注此注解后，如不传请求参数，则报错，因为此注解的属性required默认值为true，若修改成false：
                    @RequestParam("请求参数名"，required = false)
                则不报错，可配合另一属性defaultvalue在不传请求参数(或传的请求参数为空时)时赋上默认值
                    @RequestParam("请求参数名"，required = false，defaultvalue="默认值")
                    ![1](springMVC_pic/%40RequestParam.PNG)
                <2>使用@RequestHeader获取请求头信息，如请求头信息不存在，则报错，因为此注解的属性required默认值为true，若修改成false：
                     @RequestHeader("请求头信息名"，required = false)
                则不报错，可配合另一属性defaultvalue在请求头信息不存在的情况下赋上默认值
                    ![1](springMVC_pic/%40RequestHeader.PNG)
                <3>使用@CookieValue获取cookie信息
                    ![1](springMVC_pic/%40CookieValue.PNG)
            (3)通过实体类POJO获取请求参数
                ![1](springMVC_pic/%E9%80%9A%E8%BF%87%E5%AE%9E%E4%BD%93%E7%B1%BBPOJO%E8%8E%B7%E5%8F%96%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0.PNG)
                <1>需要创建pojo类，编写属性和有参、无参构造器，get、set方法
        3.域对象共享数据
            <!-- 向Request域对象共享数据 -->
            <!-- 实质上数据最终都封装到了ModelAndView对象中 -->
                (1)使用ServletAPI向request域对象共享数据
                (2)使用ModelAndView类向request域对象共享数据
                    ![1](springMVC_pic/%E4%BD%BF%E7%94%A8ModelAndView%E7%B1%BB%E5%90%91request%E5%9F%9F%E5%AF%B9%E8%B1%A1%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE.PNG)
                (3)通过Model向request域对象共享数据
                    ![1](springMVC_pic/%E4%BD%BF%E7%94%A8Model%E5%90%91request%E5%9F%9F%E5%AF%B9%E8%B1%A1%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE.PNG)
                (4)使用Map向request域对象共享数据
                    ![1](springMVC_pic/%E4%BD%BF%E7%94%A8Map%E5%90%91request%E5%9F%9F%E5%AF%B9%E8%B1%A1%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE.PNG)
                (5)使用ModelMap向request域对象共享数据
                    ![1](springMVC_pic/%E4%BD%BF%E7%94%A8ModelMap%E5%90%91request%E5%9F%9F%E5%AF%B9%E8%B1%A1%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE.PNG)
                (6)Model、ModelMap和Map之间的关系
                    ![1](springMVC_pic/Model%E3%80%81ModelMap%E5%92%8CMap%E4%B9%8B%E9%97%B4%E7%9A%84%E5%85%B3%E7%B3%BB.PNG)
            <!-- 向session域中共享数据 -->
                (1)使用ServletAPI向session域中共享数据
                    ![1](springMVC_pic/%E5%90%91session%E5%9F%9F%E4%B8%AD%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE(%E5%8E%9F%E7%94%9FServlet%E7%9A%84API).PNG)
            <!-- 向application域中共享数据 -->
                (1)使用ServletAPI向application域中共享数据
                    ![1](springMVC_pic/%E5%90%91application%E5%9F%9F%E4%B8%AD%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE(%E5%8E%9F%E7%94%9FServlet%E7%9A%84API).PNG)
        4.HttpMessageConverter(请求报文和响应报文)
            (1)简介：
                ![1](springMVC_pic/HttpMessageConverter%E7%AE%80%E4%BB%8B.PNG)
            (2)注解
                {1}@RequestBody
                    <!-- 必须使用post方式提交表单，否则报错 -->
                    ![1](springMVC_pic/%40RequestBody.PNG)
                {2}@ResponseBody
                    <!-- 响应体只能为文本，不能为java对象，可以使用json封装java对象实体信息 -->
                    ![1](springMVC_pic/%40ResponseBody.PNG)
                        <1>使用json
                            *1.导入json依赖
                                ![1](springMVC_pic/json%E7%9A%84%E4%BE%9D%E8%B5%96.PNG)
                            *2.在SpringMVC的核心配置文件中开启注解驱动
                                <mvc:annotation-driven/>
                            *3.在处理器方法上使用@ResponseBody注解标注
                            *4.将java对象作为返回值返回就会自动转换为json格式的字符串
                                ![1](springMVC_pic/%E4%BD%BF%E7%94%A8json%E5%B0%81%E8%A3%85java%E5%AF%B9%E8%B1%A1%E2%80%94%E2%80%94%E4%BD%BF%E7%94%A8%E5%93%8D%E5%BA%94%E6%8A%A5%E6%96%87.PNG)
                        <2>使用ajax
                            *1.导入vue.js和axios.js静态资源包
                                ![1](springMVC_pic/SpringMVC%E5%A4%84%E7%90%86ajax3.PNG)
                            *2.在SpringMVC核心配置文件中开启默认的servlet处理静态资源
                                ![1](springMVC_pic/SpringMVC%E5%A4%84%E7%90%86ajax4.PNG)
                            *3.编写页面
                                ![1](springMVC_pic/SpringMVC%E5%A4%84%E7%90%86ajax5.PNG)
                            *4.编写控制器方法，使用@ResponseBody标注此方法返回响应报文
                                ![1](springMVC_pic/SpringMVC%E5%A4%84%E7%90%86ajax6.PNG)
                {3}RequestEntity
                    <!-- 必须使用post方式提交表单，否则请求体为null -->
                    ![1](springMVC_pic/RequestEntity.PNG)
                {4}ResponseEntity
                    ![1](springMVC_pic/ResponseEntity.PNG)
                    <!-- 可使用此类型实现文件下载 -->
                        <1>编写下载方法：
                            ![1](springMVC_pic/ResponseEntity%E5%AE%9E%E7%8E%B0%E6%96%87%E4%BB%B6%E4%B8%8B%E8%BD%BD.PNG)
                        <2>前台点击超链接获取图片
                    <!-- 可使用此类型实现文件上传 -->
                            ![1](springMVC_pic/SpringMVC%E4%B8%8A%E4%BC%A0%E6%96%87%E4%BB%B6.PNG)
                        <1>导入依赖
                            ![1](springMVC_pic/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E7%9A%84%E4%BE%9D%E8%B5%96.PNG)
                        <2>在SpringMVC的核心配置文件配置文件上传解析器，将上传的文件封装为MultipartFile，id="multipartResolver"为固定写法
                            ![1](springMVC_pic/%E5%9C%A8SpringMVC%E7%9A%84%E6%A0%B8%E5%BF%83%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E8%A7%A3%E6%9E%90%E5%99%A8%EF%BC%8C%E5%B0%86%E4%B8%8A%E4%BC%A0%E7%9A%84%E6%96%87%E4%BB%B6%E5%B0%81%E8%A3%85%E4%B8%BAMultipartFile.PNG)
                        <3>编写控制器方法
                            ![1](springMVC_pic/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E7%9A%84%E6%8E%A7%E5%88%B6%E5%99%A8%E6%96%B9%E6%B3%95.PNG)
                        <4>文件上传的前端页面
                            ![1](springMVC_pic/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E7%9A%84%E5%89%8D%E7%AB%AF%E9%A1%B5%E9%9D%A2.PNG)
    (二)SpringMVC的视图(View)
            <!-- ![1](springMVC_pic/SpringMVC%E7%9A%84%E8%A7%86%E5%9B%BE.PNG) -->
        1.ThymeleafView
            ![1](springMVC_pic/Thymeleaf%E8%A7%86%E5%9B%BE.PNG)
        2.转发视图
            ![1](springMVC_pic/SpringMVC%E8%BD%AC%E5%8F%91%E8%A7%86%E5%9B%BE.PNG)
        3.重定向视图
            ![1](springMVC_pic/SpringMVC%E9%87%8D%E5%AE%9A%E5%90%91%E8%A7%86%E5%9B%BE.PNG)
        4.视图控制器
            请求没有其他需要处理的逻辑，只要求跳转时，即可在SpringMVC配置文件中设置视图控制器
            ![1](springMVC_pic/%E8%A7%86%E5%9B%BE%E6%8E%A7%E5%88%B6%E5%99%A8.PNG)
            ![1](springMVC_pic/%E8%A7%86%E5%9B%BE%E6%8E%A7%E5%88%B6%E5%99%A81.PNG)
    (三)SpringMVC的拦截器
            ![1](springMVC_pic/%E6%8B%A6%E6%88%AA%E5%99%A8.PNG)
        1.拦截器的配置
            ![1](springMVC_pic/%E6%8B%A6%E6%88%AA%E5%99%A8%E7%9A%84%E9%85%8D%E7%BD%AE.PNG)
        2.拦截器的使用
            (1)创建一个拦截器的类
                两种方式：
                    {1}实现HandlerInterceptor接口，并重写preHandle、postHandler、afterCompletion三个方法
                        preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)方法中，返回false表示拦截，返回true表示放行，需要放行则返回true
                            ![1](springMVC_pic/%E6%8B%A6%E6%88%AA%E5%99%A8%E7%9A%84%E4%B8%89%E4%B8%AA%E6%8A%BD%E8%B1%A1%E6%96%B9%E6%B3%95.PNG)
                    {2}继承HandlerInterceptorAdapter父类(过时方式，不推荐使用)
            (2)在SpringMVC核心配置文件中配置拦截器
                三种配置方式
                    {1}拦截所有请求
                        ![1](springMVC_pic/%E6%8B%A6%E6%88%AA%E5%99%A8%E7%9A%84%E9%85%8D%E7%BD%AE1.PNG)
                    {2}配置具体拦截规则的拦截器
                        ![1](springMVC_pic/%E9%85%8D%E7%BD%AE%E5%85%B7%E4%BD%93%E6%8B%A6%E6%88%AA%E8%A7%84%E5%88%99%E7%9A%84%E6%8B%A6%E6%88%AA%E5%99%A8.PNG)
        3.多个拦截器下，设置拦截器的优先级
            (1)
                ![1](springMVC_pic/%E9%85%8D%E7%BD%AE%E5%A4%9A%E4%B8%AA%E6%8B%A6%E6%88%AA%E5%99%A8%E7%9A%84%E4%BC%98%E5%85%88%E7%BA%A7.PNG)
            拦截器执行的先后顺序:
                ![1](springMVC_pic/%E6%8B%A6%E6%88%AA%E5%99%A8%E6%89%A7%E8%A1%8C%E7%9A%84%E5%85%88%E5%90%8E%E9%A1%BA%E5%BA%8F.PNG)
    (四)异常处理器
        1.简介：![1](springMVC_pic/%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E5%99%A81.PNG)
        2.配置自定义异常处理器
            (1)配置文件方式
                ![1](springMVC_pic/%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E6%96%B9%E5%BC%8F%E9%85%8D%E7%BD%AE%E8%87%AA%E5%AE%9A%E4%B9%89%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E5%99%A8.PNG)
            (2)注解配置方式
                {1}创建自定义异常处理类，使用注解@ControllerAdvice标注
                {2}创建自定义异常处理方法，对应相应的异常，使用注解@ExceptionHandler标注，此注解的value值为对应的异常类型的class类，value值可以有多个，匹配多个异常
                {3}方法形参使用Exception exception和Model model，exception接收异常信息；model将异常信息保存到请求域
                {4}返回值为错误页面的名称
                    ![1](springMVC_pic/%E6%B3%A8%E8%A7%A3%E6%96%B9%E5%BC%8F%E9%85%8D%E7%BD%AE%E8%87%AA%E5%AE%9A%E4%B9%89%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E5%99%A8.PNG)

# 三、RESTful
	(一)简介：
        ![1](springMVC_pic/RESTFul%E7%AE%80%E4%BB%8B.PNG)
    (二)实现：
        ![1](springMVC_pic/RESTFul%E5%AE%9E%E7%8E%B0.PNG)
    (三)由于浏览器不支持除了GET和POST方式外的请求方式(ajax方式支持除了GET和POST方式外的请求方式，但也只是部分浏览器支持)，所以需要额外配置HiddenHttpMethodFilter过滤器以支持除了GET和POST方式外的请求方式
        1.在web.xml中配置HiddenHttpMethodFilter过滤器，此过滤器的配置映射(filter-mapping)必须在设置编码的过滤器之后(过滤器的过滤顺序)
            ![1](springMVC_pic/HiddenHttpMethodFilter%E8%BF%87%E6%BB%A4%E5%99%A8.PNG)
        2.提交方式
            (1)form表单方式：
                必须是post方式且含有hidden属性的input标签：
                ![1](springMVC_pic/%E6%8F%90%E4%BA%A4form%E8%A1%A8%E5%8D%95%E5%BF%85%E9%A1%BB%E6%98%AFpost%E6%96%B9%E5%BC%8F%E4%B8%94%E5%90%AB%E6%9C%89hidden%E5%B1%9E%E6%80%A7%E7%9A%84input%E6%A0%87%E7%AD%BE.PNG)
            (2)超链接方式：
                ![1](springMVC_pic/RESTFul%E7%9A%84%E5%88%A0%E9%99%A4%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F%E5%AE%9E%E7%8E%B01.PNG)
                ![1](springMVC_pic/RESTFul%E7%9A%84%E5%88%A0%E9%99%A4%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F%E5%AE%9E%E7%8E%B02.PNG)
                ![1](springMVC_pic/RESTFul%E7%9A%84%E5%88%A0%E9%99%A4%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F%E5%AE%9E%E7%8E%B03.PNG)
# 四、注解配置SpringMVC(不使用web.xml和SpringMVC.xml)
	(一)创建初始化类，替代web.xml
            ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%911.PNG)
        1.创建初始化类，继承AbstractAnnotationConfigDispatcherServletInitializer父类
        2.重写三个方法:
            (1)getRootConfigClasses():获取spring的配置类，返回值为class数组，将spring的配置类添加在数组内，则指定了spring的配置类
                ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%911-%E6%8C%87%E5%AE%9Aspring%E7%9A%84%E9%85%8D%E7%BD%AE%E7%B1%BB.PNG)
            (2)etServletConfigClasses():指定SpringMVC的配置类，返回值为class数组，将springMVC的配置类添加在数组内，则指定了springMVC的配置类
                ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%911-%E6%8C%87%E5%AE%9AspringMVC%E7%9A%84%E9%85%8D%E7%BD%AE%E7%B1%BB.PNG)
            (3)getServletMappings():获取DispatcherServlet的映射路径/规则(url-pattern)，返回字符串数组，将url-pattern的规则以字符串形式添加进数组即可
                ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%911-%E6%8C%87%E5%AE%9ADispatcherServlet%E7%9A%84%E6%98%A0%E5%B0%84%E8%B7%AF%E5%BE%84(%E8%A7%84%E5%88%99)(url-pattern).PNG)
        3.配置过滤器，重写getServletFilters()方法，返回值是过滤器数组，在此方法内创建过滤器对象，将对象添加在返回的数组内，则配置了过滤器
            ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%911-%E9%85%8D%E7%BD%AE%E8%BF%87%E6%BB%A4%E5%99%A8.PNG)
    (二)创建Spring配置类
        1.创建一个类，使用注解@Configuration标注
    (三)创建SpringMVC配置类
        1.创建一个类，使用注解@Configuration标注
            ![1](springMVC_pic/SpringMVC纯注解开发1-创建SpringMVC的配置类.PNG)
        2.开启扫描组件：
            使用注解@ComponentScan标注此类
        3.配置视图解析器(thymeleaf)
            在此配置类中写入
                    //配置生成模板解析器
                    @Bean
                    public ITemplateResolver templateResolver(){
                        WebApplicationContext currentWebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(currentWebApplicationContext.getServletContext());
                        servletContextTemplateResolver.setPrefix("/WEB-INF/templates");
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
            ![1](springMVC_pic/SpringMVC%E7%BA%AF%E6%B3%A8%E8%A7%A3%E5%BC%80%E5%8F%912-%E9%85%8D%E7%BD%AE%E8%A7%86%E5%9B%BE%E8%A7%A3%E6%9E%90%E5%99%A8.PNG)
        4.view-contorller(视图控制器，不开启MVC的注解驱动会使控制器方法的请求映射失效)
            配置类实现WebMvcConfigurer接口，并重写方法addViewControllers(ViewControllerRegistry registry)
                ![1](springMVC_pic/SpringMVC纯注解开发2-view-contorller(视图控制器).PNG)
        5.default-servlet-handler(开启对静态资源的访问(开启默认的servlet)，不开启MVC的注解驱动会使控制器方法的请求映射失效)
            配置类实现WebMvcConfigurer接口，并重写方法configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
                ![1](springMVC_pic/SpringMVC纯注解开发2-开启对静态资源的访问(开启默认的servlet).PNG)
        6.annotation-driven(MVC的注解驱动，解除使用了试图控制器导致的控制器方法中请求映射失效，配合default-servlet-handler访问控制器映射的方法)：
            使用注解@EnableWebMvc标注此类
        7.文件上传解析器
            在此配置类中写入
                //配置文件上传解析器
                @Bean
                public MultipartResolver multipartResolver(){
                    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
                    return commonsMultipartResolver;
                }
            ![1](springMVC_pic/SpringMVC纯注解开发2-配置文件上传解析器.PNG)
        8.异常处理器
            配置类实现WebMvcConfigurer接口，并重写方法configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers)
                ![1](springMVC_pic/SpringMVC纯注解开发2-配置自定义异常处理器.PNG)
        9.拦截器
            (1)创建一个拦截器类，实现HandlerInterceptor接口，并重写preHandle、postHandler、afterCompletion三个方法
                preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)方法中，返回false表示拦截，返回true表示放行，需要放行则返回true
            (2)配置类实现WebMvcConfigurer接口，并重写方法addInterceptors(InterceptorRegistry registry)
                ![1](springMVC_pic/SpringMVC纯注解开发2-配置拦截器.PNG)
# 五、SpringMVC执行流程
    
	(一)SpringMVC的常用组件
        ![1](springMVC_pic/SpringMVC的常用组件.PNG)
    (二)DispatcherServlet的初始化过程
        <!-- DispatcherServlet本质上是一个Servlet，所以天然遵循Servlet的生命周期，所以宏观上是Servlet生命周期来进行调度 -->
        
# 六、扩展功能

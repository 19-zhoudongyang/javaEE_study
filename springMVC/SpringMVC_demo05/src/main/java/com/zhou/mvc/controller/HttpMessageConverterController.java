package com.zhou.mvc.controller;

import com.zhou.mvc.pojo.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HttpMessageConverterController {
    @RequestMapping("/toTestRequestBody")
    public String toTestRequestBody(){
        return "forward:/testRequestBody";
//        return "index";
    }
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println(requestBody);
        return "index";
    }
    @RequestMapping("/toTestRequestEntity")
    public String toTestRequestEntity(){
        return "forward:/testRequestEntity";
//        return "index";
    }
    @RequestMapping("/testRequestEntity")
    public String testRequestEntityy(RequestEntity<String> requestEntity){
        System.out.println("请求头信息："+requestEntity.getHeaders());
        System.out.println("请求体信息："+requestEntity.getBody());
        return "index";
    }
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testRequestBody(){
        return "index";
    }
    @RequestMapping("/testResponseBody_pojo")
    @ResponseBody
    public User testResponseBody_pojo(){
        return new User(1,"zhou","123456",1,"男");
    }
    @RequestMapping("/testResponseBody_ajax")
    @ResponseBody
    public String testResponseBody_ajax(String username,String password){
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        return "hello,axios";
    }
}

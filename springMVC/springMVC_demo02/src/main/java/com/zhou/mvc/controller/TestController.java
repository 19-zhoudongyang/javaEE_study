package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class TestController {
//    @RequestMapping(value="/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping(value="/target",method = RequestMethod.POST)
    public String toTarget(){
        return "target";
    }

    @RequestMapping(value = "/rest/{username}/{password}")
    public String testRest(@PathVariable(value = "username") String username, @PathVariable(value = "password") int password){
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        return "index";
    }
    @RequestMapping("/success1")
    public String testForward(){
        return "success1";
    }
    @RequestMapping("/success2")
    public String testRedirect(){
        return "success2";
    }
}

package com.zhou.boot.controller;

import com.zhou.boot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String handle01() {
        return "Hello,Spring Boot 2!";
    }


}

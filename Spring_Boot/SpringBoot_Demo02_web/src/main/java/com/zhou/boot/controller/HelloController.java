package com.zhou.boot.controller;

import com.zhou.boot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/test/user")
    public User user(){
        User user = new User();
        user.setAge(28);
        user.setName("lisi");
        return user;
    }
}

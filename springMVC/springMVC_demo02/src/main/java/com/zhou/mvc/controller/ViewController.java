package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/testThemeleafView")
    public String testThymeleafView(){
        return "success";
    }
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/hello/success1";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/hello/success2";
    }
}

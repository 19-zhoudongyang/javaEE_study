package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    @RequestMapping("/testSessionController")
    public String testSessionController(HttpSession session){
        session.setAttribute("test","hello,Session");
        return "success";
    }
}

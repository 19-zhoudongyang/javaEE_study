package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@Controller
public class ApplicationController {
    @RequestMapping("/testApplicationController")
    public String testApplicationController(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("test","hello,Session");;
        return "success";
    }
}

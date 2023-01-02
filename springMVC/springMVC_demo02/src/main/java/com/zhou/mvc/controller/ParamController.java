package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ParamController {
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest httpServletRequest) {
        //    httpServletRequest表示当前请求
        String username = httpServletRequest.getParameter("username");
        System.out.println(username);
        return "index";
    }

    @RequestMapping("/testParam")
    public String testServletAPI(String username, String password) {
        //    控制器方法直接获取请求参数
        System.out.println(username + "\n" + password);
        return "index";
    }

    @RequestMapping("/testHeader")
    public String testHeader(@RequestHeader(value = "host", required = false, defaultValue = "haha") String host) {
        //    控制器方法直接获取请求头信息
        System.out.println();
        return "index";
    }

    @RequestMapping("/testCookie")
    public String testCookie(@CookieValue(value = "JSESSIONID", required = false, defaultValue = "haha") String JSESSIONID) {
        //    控制器方法直接获取cookie:JSESSIONID
        System.out.println();
        return "index";
    }
}

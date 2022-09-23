package com.zhou.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    /**
     * 使用RESTFul模拟用户资源的增删改查
     * /users GET 查询所有用户
     * /user/id GET 根据用户id查询用户信息
     * /user POST 添加用户信息
     * /user/id DELETE 删除用户信息
     * /user PUT 修改用户信息
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有用户信息");
        return "success";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserById(){
        System.out.println("根据ID查询用户信息");
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String addUser(String username,String password){
        System.out.println("添加用户信息");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String DeleteUser(){
        System.out.println("根据ID删除用户信息");
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String UpdateUser(){
        System.out.println("修改用户信息");
        return "success";
    }
}

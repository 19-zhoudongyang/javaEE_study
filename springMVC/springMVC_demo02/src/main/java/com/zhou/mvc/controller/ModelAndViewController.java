package com.zhou.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ModelAndViewController {
    @RequestMapping("/testModelAndViewController")
    public ModelAndView testModelAndViewController() {
        ModelAndView modelAndView = new ModelAndView();
        //处理请求数据，即向请求与request共享数据
        modelAndView.addObject("test", "hello,ModelAndView");
        //设置跳转的页面
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testModelController")
    public String testModelController(Model model) {
        model.addAttribute("test", "hello,Model");
        return "success";
    }

    @RequestMapping("/testMapController")
    public String testMapController(Map<String, Object> map) {
        map.put("test", "hello,Map");
        return "success";
    }

    @RequestMapping("/testModelMapController")
    public String testModelMapController(ModelMap modelMap) {
        modelMap.addAttribute("test", "hello,ModelMap");
        return "success";
    }
}

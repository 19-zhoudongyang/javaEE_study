package com.zhou.mvc.exceptionController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    //指定异常类型
    @ExceptionHandler(ArithmeticException.class)
    //Exception exception形参接受异常信息
    public String handlerArithmeticException(Exception exception, Model model){
        //将异常添加到请求域，"ex"为key，exception为异常信息
        model.addAttribute("ex",exception);
        //跳转到显示错误的页面
        return "error";
    }
}

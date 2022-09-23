package com.zhou.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    @Pointcut("execution(* com.zhou.aop.MathCalculator.div(..))")
    public void pointCut(){}
    //前置通知
    //如果有多个方法参数，JoinPoint一定要声明在第一位
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+":除法运行，参数列表是:"+ Arrays.asList(args));
    }
    //后置通知
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+":除法结束");
    }
    //返回通知
    //returning指定方法参数中哪个参数封装返回值
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("除法正常返回，运行结果："+result);
    }
    //异常通知
    //throwing指定方法参数中哪个参数封装异常信息
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+":除法异常，异常信息:"+exception);
    }
    //环绕通知
//    @Around("pointCut()")
//    public void logAround(){
//        System.out.println("环绕通知");
//    }
}

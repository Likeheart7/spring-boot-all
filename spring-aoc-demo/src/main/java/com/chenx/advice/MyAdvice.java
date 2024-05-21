package com.chenx.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chenx
 * @description 用于增强的类
 * @create 2022-11-29 14:38
 */
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution( void com.chenx.service.impl.*.*())")
    public void pointcut(){

    }
    @Before("MyAdvice.pointcut()")
    public void beforeMethod(){
        System.out.println("beforeMethod...");
    }
    @Around("MyAdvice.pointcut()")
    public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before...");
        joinPoint.proceed();
        System.out.println("around after...");
    }
    @After("MyAdvice.pointcut()")
    public void afterMethod(){
        System.out.println("afterMethod..");
    }
    @AfterReturning("MyAdvice.pointcut()")
    public void afterReturningMethod(){
        System.out.println("afterReturningMethod..");
    }
}

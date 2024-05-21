package com.chenx.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class MessageAop {
    @Pointcut("execution(public * com.chenx.mq.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void beforeLog(JoinPoint joinpoint){
        String methodName = joinpoint.getSignature().getName(); // 方法名
        int modifier = joinpoint.getSignature().getModifiers(); // 访问修饰符，是个数字
        Class declaringType = joinpoint.getSignature().getDeclaringType();// 全类名
        String simpleName = joinpoint.getSignature().getDeclaringType().getSimpleName();// 简单类名称
        Object[] args = joinpoint.getArgs();// 传入的参数，是个数组
        log.info("{}#{}", declaringType, methodName);
        log.info("接收到的计算参数为 {}", Arrays.toString(args));
    }
}

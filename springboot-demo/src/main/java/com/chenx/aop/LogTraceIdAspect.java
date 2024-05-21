package com.chenx.aop;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LogTraceIdAspect {
    @Pointcut("execution(public * com.chenx.controller.*.*(..))")
    public void pointcut(){};

    @Before("pointcut()")
    public void beforeMethodExec(){
        ThreadContext.put("traceId", UUID.randomUUID().toString());
    }
}

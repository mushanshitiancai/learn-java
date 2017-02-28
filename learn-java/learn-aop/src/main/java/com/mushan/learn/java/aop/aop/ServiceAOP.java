package com.mushan.learn.java.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 16/12/30
 */
@Aspect
@Component
public class ServiceAOP {
    
    @Around("execution(* com.mushan.learn.java.aop.aop.PeopleService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==>> " + joinPoint);
        return joinPoint.proceed();
    }
    
    
}

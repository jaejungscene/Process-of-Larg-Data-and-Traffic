package com.example.fastcampusmysql.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class AOPClass {
    @Around("execution(* com.example.fastcampusmysql..*(..))")
    public Object advice(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("--------------------");
        Object obj = pjp.proceed();
        System.out.println("--------------------");
        return obj;
    }
}

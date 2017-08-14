package com.smart.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspectJ {

    @Pointcut("execution(* com.smart.service.UserService.*(..))")
    public void  loggingPointcut(){

    }

    @Before("loggingPointcut()")
    public void beforeMethod(JoinPoint point){
        String methodName = point.getSignature().getName();
        List args = Arrays.asList(point.getArgs()) ;
        System.out.println("Method begain with---->methodName:"+methodName+",params:"+args);
    }

    @After("loggingPointcut()")
    public void afterMethod(JoinPoint point){
        String methodName = point.getSignature().getName();
        List args = Arrays.asList(point.getArgs()) ;
        System.out.println("Method end with---->methodName:"+methodName+",params:"+args);
    }

    @AfterReturning("loggingPointcut()")
    public void afterProcess(){

    }
}

package com.mike.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect: A modularization of a concern that cuts across multiple objects.
 * 
 * AspectJ "pointcuts" is used to declare which method is going to intercept, and you should refer to this Spring AOP pointcuts guide for
 * full list of supported pointcuts expressions.
 */
@Aspect
@Component //This makes it discoverable
public class LoggingAspect {

    @Around("@annotation(com.mike.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object proceed = joinPoint.proceed();
     
        long executionTime = System.currentTimeMillis() - start;
     
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}

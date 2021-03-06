package com.mike.aop.springaop.aspectj;

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

    @Before("execution(* com.mike.aop.springaop.aspectj.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) { //< advice

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.mike.aop.springaop.aspectj.CustomerBo.addCustomerThrowException(..))",
                    throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
    }

    @Around("@annotation(com.mike.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object proceed = joinPoint.proceed();
     
        long executionTime = System.currentTimeMillis() - start;
     
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}

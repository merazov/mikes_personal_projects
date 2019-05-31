package com.mike.aop.springaop.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class HijackBeforeMethod implements MethodBeforeAdvice
{
    @Override
    public void before(Method method, Object[] args, Object target)
        throws Throwable {
            System.out.println("HijackBeforeMethod : Before method hijacked! args=" + args + " target=" + target);
    }
}

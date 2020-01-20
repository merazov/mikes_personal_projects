package com.mike.aop.guice.aspectj.simple;

import org.aspectj.lang.Aspects;

import com.google.inject.AbstractModule;
import com.mike.aop.LoggingAspect;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        requestInjection(Aspects.aspectOf(LoggingAspect.class));
    }

}

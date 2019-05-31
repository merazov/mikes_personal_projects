package com.mike.aop.springaop.aspectj.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.mike.aop.springaop.aspectj.CustomerBoImpl;
import com.mike.aop.springaop.aspectj.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public CustomerBoImpl getCustomer() {
        return new CustomerBoImpl();
    }

    @Bean
    public LoggingAspect getLoggingAspect() {
        return new LoggingAspect();
    }
}

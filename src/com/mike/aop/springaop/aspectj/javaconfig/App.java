package com.mike.aop.springaop.aspectj.javaconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mike.aop.springaop.aspectj.CustomerBo;

public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        CustomerBo customer = appContext.getBean(CustomerBo.class);
        customer.addCustomer();
        
        System.out.println("-----");
        customer.addCustomerThrowException();
        
        appContext.close();
    }
}

package com.mike.aop.springaop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "com/mike/aop/springaop/aspectJ/Spring-Customer.xml" });
        
        CustomerBo customer = (CustomerBo) appContext.getBean("customerBo");
        customer.addCustomer();
        
        System.out.println("-----");
        customer.addCustomerThrowException();
    }
}

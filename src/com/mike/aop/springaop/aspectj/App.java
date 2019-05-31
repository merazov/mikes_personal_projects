package com.mike.aop.springaop.aspectj;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
                        new String[] { "com/mike/aop/springaop/aspectJ/Spring-Customer.xml" });

        CustomerBo customer = (CustomerBo) appContext.getBean("customerBo");
        customer.addCustomer();

        System.out.println("-----");
        try {
            customer.addCustomerThrowException();
        } catch (RuntimeException e) {
            System.out.println("Swallowing exception on purpose");
        }

        appContext.close();
    }
}

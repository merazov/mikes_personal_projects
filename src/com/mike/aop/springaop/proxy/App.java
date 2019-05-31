package com.mike.aop.springaop.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "com/mike/aop/springaop/proxy/Spring-Customer.xml" });

        /*
         * This will fetch the original object so:
         * 
         * *************************
         * Customer name : Miguel Erazo
         * *************************
         * Customer website : http://www.amazon.com
         * *************************
         *
         * will be printed
         */
        CustomerService cust = (CustomerService) appContext.getBean("customerService");

        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printURL();
        System.out.println("*************************");

        /*
         * The proxy is fetched so:
         * 
         **************************
         * HijackBeforeMethod : Before method hijacked!
         * Customer name : Miguel Erazo
         * *************************
         * HijackBeforeMethod : Before method hijacked!
         * Customer website : http://www.amazon.com
         * *************************
         *
         * will be printed
         */
        cust = (CustomerService) appContext.getBean("customerServiceProxy");

        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printURL();
        System.out.println("*************************");
    }
}

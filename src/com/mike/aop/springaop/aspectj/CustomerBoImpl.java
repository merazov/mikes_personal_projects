package com.mike.aop.springaop.aspectj;

import com.mike.aop.LogExecutionTime;

public class CustomerBoImpl implements CustomerBo {

    @LogExecutionTime
    public void addCustomer() {
        System.out.println("[method] addCustomer() is running ");
    }

    public String addCustomerReturnValue() {
        System.out.println("[method] addCustomerReturnValue() is running ");
        return "abc";
    }

    public void addCustomerThrowException() {
        System.out.println("[method] addCustomerThrowException() is running ");
        throw new RuntimeException("Generic Error");
    }

    public void addCustomerAround(String name) {
        System.out.println("[method] addCustomerAround() is running, args : " + name);
    }
}

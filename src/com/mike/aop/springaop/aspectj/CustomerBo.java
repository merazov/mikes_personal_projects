package com.mike.aop.springaop.aspectj;

public interface CustomerBo {

    void addCustomer();

    String addCustomerReturnValue();

    void addCustomerThrowException();

    void addCustomerAround(String name);
}
package com.mike.aop.springaop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerService {
    private String name;
    private String url;

    public void printName() {
        System.out.println("Customer name : " + this.name);
    }

    public void printURL() {
        System.out.println("Customer website : " + this.url);
    }
}

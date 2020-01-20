package com.mike.aop.guice.aspectj.contact;

public interface ContactManager {

    public ContactManager add(Person person);

    public ContactManager remove(Person person);

    public Person lookup(String name);

}
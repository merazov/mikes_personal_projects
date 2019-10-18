package com.mike.guice.helloworld;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Tester {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new HelloWorldModule());
        TestClass testClass = injector.getInstance(TestClass.class);
        System.out.println("testClass=" + testClass);
        System.out.println("sns=" + testClass.getSns());
        System.out.println("dependency=" + testClass.getTestClassDependency());
        System.out.println("sqs=" + testClass.getTestClassDependency().getSns());
        System.out.println("2nd dependency=" + testClass.getTestClassDependency().getSecondLevelTestClassDependency());

        testClass = injector.getInstance(TestClass.class);
        System.out.println("testClass=" + testClass);
        System.out.println("sns=" + testClass.getSns());
        System.out.println("dependency=" + testClass.getTestClassDependency());
        System.out.println("sqs=" + testClass.getTestClassDependency().getSns());
        System.out.println("2nd dependency=" + testClass.getTestClassDependency().getSecondLevelTestClassDependency());
    }
}

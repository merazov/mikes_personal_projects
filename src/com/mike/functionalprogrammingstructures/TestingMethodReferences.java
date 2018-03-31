package com.mike.functionalprogrammingstructures;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

public class TestingMethodReferences {

    public void usingMethodReferenceInStaticMethod() {
        List<String> list = Arrays.asList("mike", "adrisita");
        list.forEach(System.out::println);
    }

    @RequiredArgsConstructor
    private static class Car {
        private final int id;
        
        public void getModel() {
            System.out.println("model=" + id);
        }
    }
    
    public void usingMethodReferenceInInstances() {
        List<Car> list = Arrays.asList(new Car(1), new Car(3));
        list.forEach(Car::getModel);
    }
    
    public static void main(String[] args) {
        TestingMethodReferences sut = new TestingMethodReferences();
        sut.usingMethodReferenceInStaticMethod();
        sut.usingMethodReferenceInInstances();
    }
}

package com.mike.functionalprogrammingstructures;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class TestingLambdasToMostCommonInterfaces {

    @Data
    @RequiredArgsConstructor
    public static class MyClass {
        private final Integer id;
        
        public boolean isGreaterThan5() {
            return id > 5;
        }
    }

    public static void main(String[] args) {
        Predicate<MyClass> predicate = (mc) -> mc.getId() > 5;
        List<MyClass> list = Arrays.asList(new MyClass(1), new MyClass(6));
        System.out.println(list.stream().filter(predicate).collect(Collectors.toList()));
        System.out.println(list.stream().filter(MyClass::isGreaterThan5).collect(Collectors.toList()));
    }
}

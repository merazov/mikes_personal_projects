package com.mike.functionalprogrammingstructures;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;

public class TestingLambda {

    @FunctionalInterface
    private static interface Class1 {
        public Integer decorateString();
    }
    
    @RequiredArgsConstructor
    private static class Class2 implements Class1 {
        
        private final String name;
        
        public Integer decorateString() {
            return name.length();
        }
    }
    
    public void applyTransformer(Function<Integer, String> fx) {
        System.out.println("decorated=" + fx.apply(5));
    }

    public void castInstanceMethodToFunction(String key) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println("length=" + map.computeIfAbsent(key, String::length));
    }

    public static void main(String[] args) {
        Function<Integer, String> fx = (i) -> ("'" + i + "'");
        TestingLambda sut = new TestingLambda();
        sut.applyTransformer(fx);
        sut.castInstanceMethodToFunction("mike");
        
        Class2 name = new Class2("mike");
        Function<Class2, Integer> fx2 = Class2::decorateString;
        System.out.println("name=" + fx2.apply(name));
    }
}

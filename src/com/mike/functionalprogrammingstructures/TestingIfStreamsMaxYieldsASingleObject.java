package com.mike.functionalprogrammingstructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class TestingIfStreamsMaxYieldsASingleObject {

    @Data
    @RequiredArgsConstructor
    private static class MyClass {
        private final Integer id;
    }
    
    /*
     * Output:
     * 
     * max for id=1 max=TestingIfStreamsMaxYieldsASingleObject.MyClass(id=1)
     * max for id=2 max=TestingIfStreamsMaxYieldsASingleObject.MyClass(id=2)
     * 
     */
    public static void main(String[] args) {
        List<MyClass> pojos = Arrays.asList(new MyClass(1), new MyClass(2), new MyClass(2));

        Map<Integer, List<MyClass>> eventsPerDdmg = pojos.stream()
                .collect(Collectors.groupingBy(MyClass::getId));

        for (Map.Entry<Integer, List<MyClass>> entry : eventsPerDdmg.entrySet()) {
            Optional<MyClass> opt = entry.getValue().stream()
                    .max(Comparator.comparing(MyClass::getId));
            if (opt.isPresent()) {
                System.out.println("max for id=" + entry.getKey() + " max=" + opt.get());
            }
        }
    }
}

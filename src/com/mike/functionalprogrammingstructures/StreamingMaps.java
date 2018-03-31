package com.mike.functionalprogrammingstructures;

import java.util.Map;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableMap;

public class StreamingMaps {

    public static void main(String[] args) {
        Map<Integer, String> map = ImmutableMap.of(41, "mike", 37, "adriana");
        map.forEach((k, v) -> { System.out.println("name:" + k + " age:" + v);});
        
        BiConsumer<Integer, String> bifx = new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer k, String v) {
                System.out.println("name:" + k + " age:" + v);
            }
        };
        map.forEach(bifx);
    }
}

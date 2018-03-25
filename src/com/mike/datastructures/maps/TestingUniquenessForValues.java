package com.mike.datastructures.maps;

import java.util.HashMap;
import java.util.Map;

public class TestingUniquenessForValues {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("mike", 3);
        map.put("mike", 2);
        map.put("mike", 1);
        System.out.println("key=" + map.get("mike"));
    }
}

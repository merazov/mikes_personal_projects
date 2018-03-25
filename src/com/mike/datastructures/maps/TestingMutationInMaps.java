package com.mike.datastructures.maps;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

public class TestingMutationInMaps {

    @Data
    public static class X {
        X(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        Integer id;
        String name;
        boolean useId;
    }
    
    public static void main(String[] args) {
        Map<Integer, X> map = new HashMap<>();
        X mike = new X(1, "mike");
        map.put(1, mike);
        System.out.println(map.get(1).getId() + " " + map.get(1).getName());
        // mutate
        mike.setName("adriana");
        System.out.println(map.get(1).getId() + " " + map.get(1).getName());
    }
}

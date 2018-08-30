package com.mike.functionalprogrammingstructures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterAListAndCollect {

    public static void main(String[] args) {

        List<String> legacy = Arrays.asList("abc", "bcd", "az");

        List<String> legacyRecords = legacy.stream().filter(entry -> entry.startsWith("a"))
                .collect(Collectors.toList());

        for (String legacyR : legacyRecords) {
            System.out.println("legacy:" + legacyR);
        }
    }
}

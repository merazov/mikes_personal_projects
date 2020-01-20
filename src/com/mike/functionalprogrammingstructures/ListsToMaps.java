package com.mike.functionalprogrammingstructures;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

public class ListsToMaps {

    public static void main(String[] args) {
        List<String> names = ImmutableList.of("a", "b", "c", "a");

        Map<String, List<String>> keyToNames = names.stream().collect(Collectors.groupingBy(name -> name));
        for (Map.Entry<String, List<String>> keyToRecords : keyToNames.entrySet()) {
            System.out.println("key=" + keyToRecords.getKey() + " valuesSize=" + keyToRecords.getValue().size());

            for (String record : keyToRecords.getValue()) {
                System.out.println("record=" + record);
            }
        }

        names.stream().collect(Collectors.toMap(name -> name, name -> name)); // This will throw "java.lang.IllegalStateException: Duplicate
                                                                              // key a"
    }
}

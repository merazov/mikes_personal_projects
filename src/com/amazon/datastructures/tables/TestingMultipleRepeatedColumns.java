package com.amazon.datastructures.tables;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestingMultipleRepeatedColumns {

    public static void main(String[] args) {
        Table<String, String, Integer> container = HashBasedTable.create();
        container.put("X", "B", 1);
        container.put("Y", "B", 2);
        container.put("Z", "B", 3);
        container.put("X", "H", 4);
        container.put("Y", "H", 5);
        container.put("Z", "H", 6);

        for (Map.Entry<String, Map<String, Integer>> entry : container.columnMap().entrySet()) {
            System.out.println("key=" + entry.getKey());

            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                System.out.println("\tkey2=" + entry2.getKey() + " value=" + entry2.getValue());
            }
        }
    }
}

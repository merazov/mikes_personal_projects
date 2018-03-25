package com.amazon.tables;

import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class TestingSortingWithTreeBasedTable {

    public void test1() {
        Table<String, String, Integer> universityCourseSeatTable = TreeBasedTable.create();
        universityCourseSeatTable.put("bw", "w", 2);
        universityCourseSeatTable.put("az", "z", 1);
        universityCourseSeatTable.put("ca", "a", 2);
        universityCourseSeatTable.put("mumbai", "w", 2);
        universityCourseSeatTable.put("new york", "z", 1);
        universityCourseSeatTable.put("pinocho", "a", 2);

        for (String key1 : universityCourseSeatTable.rowKeySet()) {
            System.out.println("key1=" + key1);
        }

        for (String key2 : universityCourseSeatTable.columnKeySet()) {
            System.out.println("key2=" + key2);
        }

        for (Map.Entry<String, Map<String, Integer>> entry : universityCourseSeatTable.rowMap().entrySet()) {
            System.out.println("key1=" + entry.getKey());
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                System.out.println("\tkey2=" + entry2.getKey() + " value=" + entry2.getValue());
            }
        }
    }

    public static void main(String[] args) {
        TestingSortingWithTreeBasedTable sut = new TestingSortingWithTreeBasedTable();
        sut.test1();
    }
}

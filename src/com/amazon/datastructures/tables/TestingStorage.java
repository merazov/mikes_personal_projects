package com.amazon.datastructures.tables;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class TestingStorage {
    private static Table<Integer, LocalDate, Map<Integer, String>> programEventIdDateToSpecPercentages = TreeBasedTable.create();

    private void storeSpec(Integer programMixEventId, LocalDate currentDate, Integer myInt, String name) {
        Map<Integer, String> specToDecimal = programEventIdDateToSpecPercentages.get(programMixEventId, currentDate);
        if (specToDecimal == null) {
            specToDecimal = new HashMap<>();
        }
        specToDecimal.put(myInt, name);
        programEventIdDateToSpecPercentages.put(programMixEventId, currentDate, specToDecimal);
    }

    public static void main(String[] args) {
        TestingStorage sut = new TestingStorage();
        LocalDate now = LocalDate.now();
        sut.storeSpec(1, now, 1, "mike");
        sut.storeSpec(1, now, 2, "adriana");
        sut.storeSpec(1, now, 3, "sebas");
        sut.storeSpec(1, now, 4, "lobo");

        sut.storeSpec(2, now.plusDays(1), 1, "abelito");
        sut.storeSpec(2, now.plusDays(1), 2, "mamita");
        sut.storeSpec(2, now.plusDays(1), 3, "mike");
        sut.storeSpec(2, now.plusDays(1), 4, "pepito");
        sut.storeSpec(2, now.plusDays(1), 5, "anita");
        
        Map<Integer, String> stored = programEventIdDateToSpecPercentages.get(1, now);
        System.out.println("stored=" + stored);
        
        Map<Integer, String> stored2 = programEventIdDateToSpecPercentages.get(2, now.plusDays(1));
        System.out.println("stored 2=" + stored2);
    }
}

package com.mike.tables;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ImmutableTable;

public class BuildingTableFromEmptyEntries {

    public static void main(String[] args) {
        ImmutableTable.Builder<String, String, String> programMixActualsTableBuilder = ImmutableTable.builder();

        ImmutableTable<String, String, String> table = programMixActualsTableBuilder.build();

        System.out.println("table=" + table);
        
        ArrayTable<String, String, String> arrayTable = ArrayTable.create(table);
        
        if (arrayTable == null) {
            System.out.println("arrayTable is null!");
        }
    }
}

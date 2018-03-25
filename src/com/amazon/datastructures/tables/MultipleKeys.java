package com.amazon.datastructures.tables;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.map.MultiKeyMap;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class MultipleKeys {

    public void testGoogleGuavaTable1() {
        Table<String, String, Integer> universityCourseSeatTable = HashBasedTable.create();
        universityCourseSeatTable.put("a", "b", 123);
        universityCourseSeatTable.put("a", "c", 456);
        
        System.out.println("key1=" + universityCourseSeatTable.get("a","b"));
        System.out.println("key2=" + universityCourseSeatTable.get("a","c"));
    }
    
    public void testGoogleGuavaTable2() {
        List<String> universityRowTable = Lists.newArrayList("Mumbai", "Harvard");
        List<String> courseColumnTables = Lists.newArrayList("Chemical", "IT", "Electrical");
        Table<String, String, Integer> universityCourseSeatTable = ArrayTable.create(universityRowTable, courseColumnTables);
        
        System.out.println("key1=" + universityCourseSeatTable.get("Mumbai", "Harvard"));
    }
    
    public void testApacheMultiKeyMap() {
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();

        multiKeyMap.put("Key 1A","Key 1B","Value 1");
        multiKeyMap.put("Key 2A","Key 2B","Value 2");
        multiKeyMap.put("Key 3A","Key 3B","Value 3");
        
        MapIterator<MultiKey<? extends String>, String> it = multiKeyMap.mapIterator();

        System.out.println("\n");
        while (it.hasNext()) {
            it.next();
        
            System.out.println("value=" + it.getValue());
            
            MultiKey<? extends String> mk = it.getKey();
        
            // Option 1
            System.out.println(mk.getKey(0));
            System.out.println(mk.getKey(1));
        
            // Option 2
            //for (Object subkey : mk.getKeys()) {
            //  System.out.println(subkey);
            //}
        }
    }

    public void testMultipleRepeatedColumnKeys() {
        MultiKeyMap<String, String, String> multiKeyMap = new MultiKeyMap<>();

        multiKeyMap.put("Key 1A", "B", 2);
        multiKeyMap.put("Key 2A", "B", "Value 2");
        multiKeyMap.put("Key 3A", "Key 3B", "Value 3");
        
        
    }

    public void testApacheMultiKeyMap2() {
        MultiKeyMap multiKeyMap = new MultiKeyMap();

        multiKeyMap.put("Key 1A", "Key 1B", 2);
        multiKeyMap.put("Key 2A", "Key 2B", "Value 2");
        multiKeyMap.put("Key 3A", "Key 3B", "Value 3");
        
        MapIterator<MultiKey<? extends String>, Integer> it = multiKeyMap.mapIterator();

        System.out.println("\n");
        while (it.hasNext()) {
            it.next();
        
            //if(it.getValue().getClass().equals(Integer.class)) {
              //  System.out.println("value=" + Integer.toString( (Integer)it.getValue() ));
            //} else {
                System.out.println("value=" + it.getValue());
            //}
            
            MultiKey mk = (MultiKey) it.getKey();
        
            // Option 1
            System.out.println(mk.getKey(0));
            System.out.println(mk.getKey(1));
        
            // Option 2
            //for (Object subkey : mk.getKeys()) {
            //  System.out.println(subkey);
            //}
        } 
    }
    
    public static void main(String[] args) {
        MultipleKeys keys = new MultipleKeys();
        //keys.testGoogleGuavaTable1();
        //keys.testGoogleGuavaTable2();
        //keys.testApacheMultiKeyMap();
        keys.testApacheMultiKeyMap2();
    }
}

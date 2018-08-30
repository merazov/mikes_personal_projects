package com.mike.generics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ProhibitingComparisonBetweenObjectsOfDifferentTypes {

    private static class Fruit {
        
    }
    
    private static class Apple extends Fruit implements Comparable<Apple>{
        @Override
        public int compareTo(Apple o) {
            // TODO Auto-generated method stub
            return 0;
        }        
    }
    
    private static class Orange extends Fruit implements Comparable<Orange>{
        @Override
        public int compareTo(Orange o) {
            // TODO Auto-generated method stub
            return 0;
        }        
    }
    
    public static void main(String[] args) {
        Collections.max(Arrays.<Fruit>asList());
        
        Comparator<Orange> comp = (o1, o2) -> { return -1; };
    }
}

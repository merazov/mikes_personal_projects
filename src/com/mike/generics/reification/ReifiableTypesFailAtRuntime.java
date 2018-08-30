package com.mike.generics.reification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReifiableTypesFailAtRuntime {

    public static void main(String[] args) {
        Integer[] ints = new Integer[] { 1, 2, 3 };
        Number[] nums = ints;
        nums[2] = 3.14; // array store exception
        
        Arrays.asList(a);
        List<Integer> ints2 = new ArrayList<Integer>();
        List<? extends Number> numbers = ints2;
        numbers.add(3);
    }
}

package com.mike.reifiability;

import java.util.ArrayList;
import java.util.List;

public class ImmutabilityWithExtends {

    public static void main(String[] args) {
        List<? extends Number> nums = new ArrayList<>();
        
        nums.remove(0); //< not a compile time error, so immutability is not assured!
        nums.add(1); //<compile time error since the Get/Put principle is not met
    }
}

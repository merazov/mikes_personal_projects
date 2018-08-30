package com.mike.generics;

import java.util.ArrayList;
import java.util.List;

public class UncheckedCast1 {

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        List rawList = ints;
        rawList.add("");
        for (Integer i : ints);
    }
}

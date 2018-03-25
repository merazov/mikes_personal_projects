package com.amazon.subtyping;

import java.util.ArrayList;
import java.util.List;

public class TestingSubtypingWithLists {

    public static void main(String[] args) {
        List<? extends List<Integer>> myList1 = new ArrayList<ArrayList<Integer>>();
        List<ArrayList<Integer>> myList2 = new ArrayList<ArrayList<Integer>>();
        List<List<Integer>> myList3 = new ArrayList<ArrayList<Integer>>(); //Does not work because of Generic's invariance
    }
}

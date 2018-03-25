package com.amazon.datastructures.lists;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class IteratingOverTwoLists {

    public static void main(String[] args) {
        List<Integer> listOrderedByStartDate = ImmutableList.of(1, 5);
        List<Integer> listOrderedByEndDate = ImmutableList.of(3, 4);
        
        int index1 = 0, index2 = 0;
        while (index1 < listOrderedByStartDate.size() || index2 < listOrderedByEndDate.size()) {
            if (index1 < listOrderedByStartDate.size() && 
                    (index2 >= listOrderedByEndDate.size() || listOrderedByStartDate.get(index1) < listOrderedByEndDate.get(index2))) {
                //System.out.println("[listOrderedByStartDate] element:" + listOrderedByStartDate.get(index1));
                index1++;
            } else {
                //System.out.println("[listOrderedByEndDate] element:" + listOrderedByEndDate.get(index2));
                index2++;
            }
        }

        listOrderedByStartDate = ImmutableList.of(1, 5, 9, 10, 11);
        listOrderedByEndDate = ImmutableList.of(3, 6);

        index1 = 0; index2 = 0;
        while (index1 < listOrderedByStartDate.size() || index2 < listOrderedByEndDate.size()) {
            if (index1 < listOrderedByStartDate.size() && 
                    (index2 >= listOrderedByEndDate.size() || listOrderedByStartDate.get(index1) < listOrderedByEndDate.get(index2))) {
                System.out.println("[listOrderedByStartDate] element:" + listOrderedByStartDate.get(index1));
                index1++;
            } else {
                System.out.println("[listOrderedByEndDate] element:" + listOrderedByEndDate.get(index2));
                index2++;
            }
        }
    }
}

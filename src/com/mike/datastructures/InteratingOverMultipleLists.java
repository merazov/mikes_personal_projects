package com.mike.datastructures;

import java.time.LocalDate;

import com.google.common.collect.ImmutableList;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class InteratingOverMultipleLists {

    private static final ImmutableList<A> A = ImmutableList.of(new A(LocalDate.of(2018, 01, 01)),
                                                               new A(LocalDate.of(2018, 01, 05)),
                                                               new A(LocalDate.of(2018, 01, 10)));

    private static final ImmutableList<B> B = ImmutableList.of(new B(LocalDate.of(2018, 01, 03)),
                                                               new B(LocalDate.of(2018, 01, 07)),
                                                               new B(LocalDate.of(2018, 01, 12)));

    private static final ImmutableList<B> C = ImmutableList.of(new B(LocalDate.of(2018, 01, 02)),
                                                               new B(LocalDate.of(2018, 01, 06)),
                                                               new B(LocalDate.of(2018, 01, 14)));
    
    private void executeCodeForA(A a) {
        System.out.println("[A] Executing code for:" + a);
    }

    private void executeCodeForB(B b) {
        System.out.println("[B] Executing code for:" + b);
    }

    public void firstWayToDoThis() {
        int indexA = 0;
        int indexB = 0;

        while (indexA < A.size() || indexB < B.size()) {
            if (indexA < A.size() && indexB < B.size()) {
                if (A.get(indexA).getStartDate().isBefore(B.get(indexB).getEndDate())) {
                    executeCodeForA(A.get(indexA++));
                } else {
                    executeCodeForB(B.get(indexB++));
                }
            } else if (indexA < A.size()) {
                executeCodeForA(A.get(indexA++));
            } else {
                executeCodeForB(B.get(indexB++));
            }
        }
    }

    public void secondWayToDoThis() {
        int indexA = 0;
        int indexB = 0;

        while (indexA < A.size() || indexB < B.size()) {
            if( indexA < A.size() && ( indexB >= B.size() || A.get(indexA).getStartDate().isBefore(B.get(indexB).getEndDate()) ) ) {
                executeCodeForA(A.get(indexA++));
            } else {
                executeCodeForB(B.get(indexB++));
            }
        }
    }
    
    @Data
    @RequiredArgsConstructor
    static class A {
        private final LocalDate startDate;
    }

    @Data
    @RequiredArgsConstructor
    static class B {
        private final LocalDate endDate;
    }

    public static void main(String[] args) {
        InteratingOverMultipleLists alg = new InteratingOverMultipleLists();
        alg.firstWayToDoThis();
        System.out.println("-------");
        alg.secondWayToDoThis();
    }
}

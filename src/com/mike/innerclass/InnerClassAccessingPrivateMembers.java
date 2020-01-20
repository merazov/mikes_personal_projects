package com.mike.innerclass;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InnerClassAccessingPrivateMembers {

    private final String str;

    @RequiredArgsConstructor
    public static class Dto {
        private final String str;
    }

    public static class Converter {

        public Dto toDto() {
            
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}

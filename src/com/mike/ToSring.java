package com.mike;

public class ToSring {

    public static class ToTest {
        public Integer age;
        public String name;
        
        public ToTest(Integer age, String name) {
            this.age  = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ToTest toTest = new ToTest(10, "adnan");
        
        System.out.println("toTest=" + toTest);
    }
}

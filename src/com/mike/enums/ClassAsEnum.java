package com.mike.enums;

public enum ClassAsEnum {
    MIKE,
    PEPE;

    public static void main(String[] args) {
        for (ClassAsEnum val : ClassAsEnum.values()) {
            System.out.println("name: " + val.name());
            // System.out.println(ClassAsEnum.valueOf("")); //<exception
            // System.out.println(ClassAsEnum.valueOf("mike")); //<exception
        }

        System.out.println("ClassAsEnum.MIKE.compareTo(ClassAsEnum.PEPE)=" + ClassAsEnum.MIKE.compareTo(ClassAsEnum.PEPE));

        System.out.println(ClassAsEnum.valueOf("MIKE"));
        System.out.println("valueOf:" + Enum.valueOf(ClassAsEnum.class, "MIKE"));

        // Subtyping
        Enum<ClassAsEnum> enm = ClassAsEnum.MIKE;
        System.out.println("enm.name: " + enm.name());
    }
}

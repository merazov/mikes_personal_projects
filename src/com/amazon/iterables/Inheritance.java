package com.amazon.iterables;

import java.util.ArrayList;
import java.util.List;

public class Inheritance {
    public static class Car {
        public String whatIam() {
            return "car";
        }
    }

    public static class Toyota extends Car {
        public String whatIam() {
            return "toyota";
        }
    }

    public static class BMW extends Car {
        public String whatIam() {
            return "bmw";
        }
    }

    public static void addToyota(List<Car> autos) {
        autos.add(new Toyota());
    }
    
    public static void readCar(List<? extends Car> autos) {
        //autos.add(new Toyota()); <- cannot add!
        for(Car auto : autos) {
            System.out.println(auto.whatIam());
        }
    }

    public static void main(String[] args) {
        List<BMW> myAutos = new ArrayList<BMW>();
        // addToyota(myAutos); explodes!!!

        List<Car> myAutos2 = new ArrayList<Car>();
        addToyota(myAutos2);
        
        List<BMW> myAutos3 = new ArrayList<BMW>();
        // addToyota(myAutos3); explodes!!!
        
        //Read
        List<BMW> myAutos4 = new ArrayList<BMW>();
        readCar(myAutos4);

        List<Car> myAutos5 = new ArrayList<Car>();
        readCar(myAutos5);
        
        List<Toyota> myAutos6 = new ArrayList<Toyota>();
        readCar(myAutos6); 
    }
}

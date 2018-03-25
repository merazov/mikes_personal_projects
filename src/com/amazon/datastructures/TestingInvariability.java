package com.amazon.datastructures;

import java.util.ArrayList;
import java.util.List;

public class TestingInvariability {

    /*
     * Given a list of any subtype of Car, add any subtype of Car
     */
    public void addCarV2(List<? extends Car> cars) {
        car.add(new Toyota(""));
        car.add(new BMW(""));

        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /*
     * Given a list of Cars, add cars of different sub-types
     */
    public void addCar(List<Car> cars) {
        cars.add(new Toyota(""));
        cars.add(new BMW(""));
    }

    public static void main(String[] args) {
        TestingInvariability sut = new TestingInvariability();

        sut.addCar(new ArrayList<Car>());
        sut.addCar(new ArrayList<BMW>());

        sut.addCarV2(new ArrayList<Car>());
        sut.addCarV2(new ArrayList<BMW>());
        
        List<Toyota> toyotas = new ArrayList<>();
        List<Car> cars = toyotas; //because of invariance
        
        List<Car> cars2 = new ArrayList<>();
        List<Toyota> toyotas2 = cars2;//because of logic
    }
}
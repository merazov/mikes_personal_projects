package com.mike.iterables;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mike.datastructures.BMW;
import com.mike.datastructures.Car;
import com.mike.datastructures.Toyota;

public class InheritanceCollections {

    /**
     * @param immutableCollection
     */
    public void readWithImmutableCollections(ImmutableCollection<Car> cars) {
        cars.add(new Car("car1"));
        cars.add(new Toyota("toyota1"));
        cars.add(new BMW("bmw1"));

        for (Car car : cars) {
            System.out.println("my car=" + car.getMyName());
        }
    }

    /**
     * Receive all types of collections (immutable and mutable) of cars for read/write permissions
     * 
     * @param cars
     */
    public void readWriteWithCollections(Collection<Car> cars) {
        cars.add(new Car("car1"));
        cars.add(new Toyota("toyota1"));
        cars.add(new BMW("bmw1"));

        for (Car car : cars) {
            System.out.println("my car=" + car.getMyName());
        }
    }

    public static void main(String[] args) {
        InheritanceCollections classToTest = new InheritanceCollections();
        classToTest.readWriteWithCollections(new ArrayList<>());
        classToTest.readWriteWithCollections(ImmutableList.of());
        
        //classToTest.readWithImmutableCollections(new ArrayList<>()); //< does not compile!
        classToTest.readWithImmutableCollections(ImmutableList.of());
    }
}

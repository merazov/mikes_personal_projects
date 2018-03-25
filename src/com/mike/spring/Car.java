package com.mike.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;

//@Component
//@Data
@Getter
public class Car {
    @Autowired
    private Engine engine;
    private Transmission transmission;
    
    //@Autowired
    /*public Car(Engine engine, Transmission transmission) {
        this.engine = engine;
        this.transmission = transmission;
    }*/
}

package com.mike.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan("com.amazon.spring.scan")
//@Import(value = {Engine.class, Transmission.class})
@Import(value = {Car.class})
public class Config {
    @Bean
    public Engine engine() {
        return new Engine("v8", 5);
    }
 
    @Bean
    public Transmission transmission() {
        return new Transmission("sliding");
    }
}

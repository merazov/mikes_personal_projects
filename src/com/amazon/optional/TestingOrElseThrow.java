package com.amazon.optional;

import java.util.Optional;

public class TestingOrElseThrow {

    public static void main(String[] args) {
        Optional<String> opt = Optional.of("mike");
        String content = opt.orElseThrow(RuntimeException::new);
        System.out.println("content=" + content);
        
        String ddmg = Optional.of("123ddmg").orElseThrow(RuntimeException::new);
        System.out.println("ddmg=" + ddmg);
    }
}

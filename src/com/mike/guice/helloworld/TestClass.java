package com.mike.guice.helloworld;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestClass {
    
    @Getter
    private final TestClassDependency testClassDependency;
}

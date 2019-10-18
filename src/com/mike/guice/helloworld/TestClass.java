package com.mike.guice.helloworld;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestClass {

    private final TestClassDependency testClassDependency;

    private final String sns;
}

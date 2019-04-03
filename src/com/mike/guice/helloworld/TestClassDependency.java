package com.mike.guice.helloworld;

import javax.inject.Inject;

import lombok.Getter;

public class TestClassDependency {

    @Getter
    private final SecondLevelTestClassDependency secondLevelTestClassDependency;

    @Inject
    public TestClassDependency(SecondLevelTestClassDependency secondLevelTestClassDependency) {
        this.secondLevelTestClassDependency = secondLevelTestClassDependency;
    }
}

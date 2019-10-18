package com.mike.guice.helloworld;

import lombok.Getter;

@Getter
public class TestClassDependency {

    private final SecondLevelTestClassDependency secondLevelTestClassDependency;

    private final String sns;
    
    //@Inject
    public TestClassDependency(SecondLevelTestClassDependency secondLevelTestClassDependency,
                    String sns) {
        this.secondLevelTestClassDependency = secondLevelTestClassDependency;
        this.sns = sns;
    }
}

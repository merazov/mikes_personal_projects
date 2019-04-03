package com.mike.guice.helloworld;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class HelloWorldModule extends AbstractModule {
    @Override
    protected void configure() {}

    @Provides
    @Singleton
    public SecondLevelTestClassDependency provideSecondLevelTestClassDependency() {
        return new SecondLevelTestClassDependency();
    }

    @Provides
    @Singleton
    public TestClass provideTestClass(TestClassDependency testClassDependency) {
        return new TestClass(testClassDependency);
    }
}

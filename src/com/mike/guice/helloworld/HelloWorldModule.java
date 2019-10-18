package com.mike.guice.helloworld;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class HelloWorldModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind(String.class).annotatedWith(Names.named("sns")).toInstance(snsArn());
        //bind(String.class).annotatedWith(Names.named("sqs")).toInstance(sqsArn());
    }

    @Provides
    @Singleton
    public SecondLevelTestClassDependency provideSecondLevelTestClassDependency() {
        return new SecondLevelTestClassDependency();
    }

    @Provides
    @Singleton
    public TestClass provideTestClass(TestClassDependency testClassDependency,
                    @Named(value = "sns") String sns) {
        return new TestClass(testClassDependency, sns);
    }

    @Provides
    @Singleton
    public TestClassDependency TestClassDependency(SecondLevelTestClassDependency testClassDependency,
                    @Named(value = "sqs") String sqs) {
        return new TestClassDependency(testClassDependency, sqs);
    }

    @Provides
    @Singleton
    @Named("sns")
    public String snsArn() {
        return "snsArn";
    }

    @Provides
    @Singleton
    @Named("sqs")
    public String sqsArn() {
        return "sqsarn";
    }
}

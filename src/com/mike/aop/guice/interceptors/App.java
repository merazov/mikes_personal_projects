package com.mike.aop.guice.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;

public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }

    public static class TextEditor {
        private SpellChecker spellChecker;

        @Inject
        public TextEditor(SpellChecker spellChecker) {
            this.spellChecker = spellChecker;
        }

        public void makeSpellCheck() {
            spellChecker.checkSpelling();
        }
    }

    //Binding Module
    public static class TextEditorModule extends AbstractModule {
        @Override

        protected void configure() {
            bind(SpellChecker.class).to(SpellCheckerImpl.class);
            bindInterceptor(Matchers.any(),
                            Matchers.annotatedWith(CallTracker.class),
                            new CallTrackerService());
        }
    }

    //spell checker interface
    public static interface SpellChecker {
        public void checkSpelling();
    }

    //spell checker implementation
    public static class SpellCheckerImpl implements SpellChecker {
        @Override
        @CallTracker
        public void checkSpelling() {
            System.out.println("Inside checkSpelling.");
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface CallTracker {
    }

    public static class CallTrackerService implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("Before " + invocation.getMethod().getName());
            Object result = invocation.proceed();
            System.out.println("After " + invocation.getMethod().getName());
            return result;
        }
    }
}

package com.mike.aop.guice.aspectj.julien;

import static org.aspectj.lang.Aspects.aspectOf;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.mike.aop.guice.aspectj.julien.aspects.ProfileVerification;
import com.mike.aop.guice.aspectj.julien.auth.UserProfileChecker;
import com.mike.aop.guice.aspectj.julien.auth.dumb.DumbUserProfileChecker;
import com.mike.aop.guice.aspectj.julien.contact.ContactManager;
import com.mike.aop.guice.aspectj.julien.contact.Person;
import com.mike.aop.guice.aspectj.julien.contact.simple.InMemoryContactManager;

public class Main {

    public static void main(String[] args) {
        new Main().execute();
    }

    private Module guiceModule = new AbstractModule() {
        @Override
        protected void configure() {

            bind(ContactManager.class)
                    .to(InMemoryContactManager.class)
                    .in(Singleton.class);

            bind(UserProfileChecker.class)
                    .to(DumbUserProfileChecker.class)
                    .in(Singleton.class);

            requestInjection(aspectOf(ProfileVerification.class));

        }
    };

    private Injector injector = Guice.createInjector(guiceModule);

    private void execute() {
        ContactManager contacts = injector.getInstance(ContactManager.class);
        UserProfileChecker profileChecker = injector.getInstance(UserProfileChecker.class);

        profileChecker.login("Julien", "secret");

        contacts.add(new Person("Julien Ponge", "julien.ponge@gmail.com"));
        contacts.add(new Person("Jean-Jacques", "jean.jacques@gmail.com"));

        profileChecker.logout();
        profileChecker.login("Jean-Jacques", "1234");

        System.out.println(contacts.lookup("Julien Ponge"));

        profileChecker.logout();
        contacts.add(new Person("Mr Bean", "mrbean@gmail.com"));
    }

}

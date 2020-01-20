package com.mike.aop.guice.aspectj.contact.simple;

import static com.mike.aop.guice.aspectj.auth.UserProfile.ADMIN;
import static com.mike.aop.guice.aspectj.auth.UserProfile.USER;

import java.util.HashSet;
import java.util.Set;

import com.mike.aop.guice.aspectj.auth.RequiresProfile;
import com.mike.aop.guice.aspectj.auth.WithUserProfileVerification;
import com.mike.aop.guice.aspectj.contact.ContactManager;
import com.mike.aop.guice.aspectj.contact.Person;

@WithUserProfileVerification
public class InMemoryContactManager implements ContactManager {

    private final Set<Person> contacts = new HashSet<Person>();

    @RequiresProfile(ADMIN)
    public ContactManager add(Person person) {
        contacts.add(person);
        return this;
    }

    @RequiresProfile(ADMIN)
    public ContactManager remove(Person person) {
        contacts.remove(person);
        return this;
    }

    @RequiresProfile(USER)
    public Person lookup(String name) {
        for (Person person : contacts) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

}
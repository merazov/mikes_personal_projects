package com.mike.aop.guice.aspectj.julien.contact.simple;

import java.util.HashSet;
import java.util.Set;

import com.mike.aop.guice.aspectj.julien.auth.RequiresProfile;
import com.mike.aop.guice.aspectj.julien.auth.WithUserProfileVerification;
import com.mike.aop.guice.aspectj.julien.contact.ContactManager;
import com.mike.aop.guice.aspectj.julien.contact.Person;

import static com.mike.aop.guice.aspectj.julien.auth.UserProfile.ADMIN;
import static com.mike.aop.guice.aspectj.julien.auth.UserProfile.USER;

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

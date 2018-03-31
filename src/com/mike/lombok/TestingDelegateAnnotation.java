package com.mike.lombok;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

public class TestingDelegateAnnotation {

    public static interface HasContactInformation {

        String getFirstName();
        void setFirstName(String firstName);
        String getFullName();
        String getLastName();
        void setLastName(String lastName);
        String getPhoneNr();
        void setPhoneNr(String phoneNr);
    }

    @Data
    public static class ContactInformationSupport implements HasContactInformation {

        private String firstName;
        private String lastName;
        private String phoneNr;

        @Override
        public String getFullName() {
            return getFirstName() + " " + getLastName();
        }
    }

    @RequiredArgsConstructor
    public static class User implements HasContactInformation {
        
        // Whichever other User-specific attributes

        @Delegate(types = { HasContactInformation.class })
        private final ContactInformationSupport contactInformation;

        // User itself will implement all contact information by delegation
    }

    public static void main(String[] args) {
        ContactInformationSupport info = new ContactInformationSupport();
        info.setFirstName("mike");
        info.setFirstName("erazo");
        
        User user = new User(info);
        System.out.println("firstName=" + user.getFirstName());
    }
}

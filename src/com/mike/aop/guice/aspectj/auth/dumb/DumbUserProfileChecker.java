package com.mike.aop.guice.aspectj.auth.dumb;

import static com.mike.aop.guice.aspectj.auth.UserProfile.ADMIN;
import static com.mike.aop.guice.aspectj.auth.UserProfile.USER;
import static com.mike.aop.guice.aspectj.auth.UserProfile.ANONYMOUS;

import com.mike.aop.guice.aspectj.auth.UserProfile;
import com.mike.aop.guice.aspectj.auth.UserProfileChecker;

public class DumbUserProfileChecker implements UserProfileChecker {

    private UserProfile userProfile = ANONYMOUS;

    public UserProfile getCurrentUserProfile() {
        return userProfile;
    }

    public UserProfile login(String login, String password) {
        if (login.equals("Julien") && password.equals("secret")) {
            userProfile = ADMIN;
        } else if (login.equals("Jean-Jacques") && password.equals("1234")) {
            userProfile = USER;
        } else {
            userProfile = ANONYMOUS;
        }

        return getCurrentUserProfile();
    }

    public UserProfile logout() {
        userProfile = ANONYMOUS;
        return userProfile;
    }

}
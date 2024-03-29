package com.mike.aop.guice.aspectj.julien.auth;

public interface UserProfileChecker {

    public UserProfile getCurrentUserProfile();

    public UserProfile login(String login, String password);

    public UserProfile logout();

}

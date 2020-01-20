package com.mike.aop.guice.aspectj.julien.aspects;

import com.google.inject.Inject;
import com.mike.aop.guice.aspectj.julien.auth.RequiresProfile;
import com.mike.aop.guice.aspectj.julien.auth.UserProfile;
import com.mike.aop.guice.aspectj.julien.auth.UserProfileChecker;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.mike.aop.guice.aspectj.julien.auth.UserProfile.ADMIN;
import static com.mike.aop.guice.aspectj.julien.auth.UserProfile.USER;

@Aspect
public class ProfileVerification {

    @Inject
    UserProfileChecker userProfileChecker;

    @Before("execution( * *(..) ) && @annotation( required ) && within( @WithUserProfileVerification * )")
    public void verify(RequiresProfile required) {
        UserProfile expected = required.value();
        UserProfile current = userProfileChecker.getCurrentUserProfile();

        if (insufficientProfile(expected, current)) {
            throw new RuntimeException("The current user profile (" + current + ") is not sufficient: " + required);
        }
    }

    private boolean insufficientProfile(UserProfile required, UserProfile current) {
        return (required == ADMIN && current != ADMIN)
                || (required == USER && (current != USER && current != ADMIN));
    }

}

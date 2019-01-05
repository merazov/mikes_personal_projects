package com.mike.testing.cucumber.helloworld;

public class LoginUtil {
    /**
     * 
     * @param userName
     * @param password
     * @return true if the login successful, else false
     */
    public static boolean isLoginSuccessful(String userName, String password) {

        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        if ("krishna".equals(userName) && "password123".equals(password)) {
            return true;
        }

        return false;
    }
}

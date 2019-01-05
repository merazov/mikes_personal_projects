package com.mike.testing.cucumber.helloworld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest {
    private String userName;

    @Given("^I have chosen to Login$")
    public void iHaveChosenToLogin() throws Exception {
        System.out.println("Opening login page");
    }

    @When("^I log in with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLogInWithCorrectAnd(String userName, String password) throws Exception {

        if (LoginUtil.isLoginSuccessful(userName, password)) {
            this.userName = userName;
            System.out.println("Login successful");
            return;
        }
        assertFalse("Wrong Credentials. userName " + userName, true);
    }

    @Then("^I should be logged in successfully$")
    public void iShouldBeLoggedInSuccessfully() throws Exception {
        System.out.println("Logged in Successfully");
    }

    @Then("^I should see a personalized greeting message$")
    public void iShouldSeeAPersonalizedGreetingMessage() throws Exception {
        System.out.println("Welcome " + userName);
        assertTrue(true);
    }
}

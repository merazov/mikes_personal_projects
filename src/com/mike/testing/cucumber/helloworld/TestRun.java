package com.mike.testing.cucumber.helloworld;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/com/mike/testing/cucumber/helloworld/features/", glue = "com.mike.testing.cucumber.helloworld", plugin = {
  "pretty" })
public class TestRun {

}
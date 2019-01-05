Feature: Login

 Login should be quick and friendly.
 
 Scenario: Successful Login
  Users should be logged in successfully by providing correct username and password.
  
  Given I have chosen to Login
  When I log in with user name "krishna" and password "password123"
  Then I should be logged in successfully
  And I should see a personalized greeting message
Feature: User Login
  As a registered user
  I want to log in with valid credentials
  So that I can access my account and be redirected to the homepage

  Scenario: Validate user can log in with a registered email and password
    Given the user on the Magento login page
    When the user clicks on the Sign In button in home page
    And the user enters the email registeredemail@example.com
    And the user enters the password
    And the user clicks on the Sign In button
    Then the user should be navigated to the homepage
    And the welcome message should contain the user's first name Mohamed and last name Khaled
Feature: Account Creation Error Validation
  Scenario: Validate an error message is displayed when user leaves Confirm Password field empty
    Given I am on the Magento account creation page
    When I click on the Create an Account button in home page
    And I enter a valid First Name
    And I enter a valid Last Name
    And I enter a valid Email format
    And I enter a Password
    And I leave the Confirm Password field empty
    And I click on Create an Account
    Then I should see an error message indicating that the Confirm Password field is required

  Scenario: Validate an error message is displayed when user enter password and confirm password does not match each other
    Given I am on the Magento account creation page
    When I click on the Create an Account button in home page
    And I enter a valid First Name
    And I enter a valid Last Name
    And I enter a valid Email format
    And I enter a Password
    And Enter Confirm Password does not match Password
    And I click on Create an Account
    Then I should see an error message indicating that enter the same value in password again

  Scenario: Validate that user after register with valid data will be navigated to My Account page
    Given I am on the Magento account creation page
    When I click on the Create an Account button in home page
    And I enter a valid First Name
    And I enter a valid Last Name
    And I enter a valid Email format
    And I enter a Password
    And Enter Confirm Password match Password
    And I click on Create an Account
    Then User navigated to account page after click on Create an Account from Homepage


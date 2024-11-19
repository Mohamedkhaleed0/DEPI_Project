Feature: Validate Hot Sellers Section on Landing Page

  Scenario: Validate that Hot Sellers is displayed and each card contains price in dollar sign
    Given The user opens the home page
    When The user scrolls down to the Hot Sellers section
    Then The user should see each card with price in dollar
   And Each card should display price as Double, not Integer

  Scenario: Validate that each card contains "Add to Cart" button and clickable
    Given The user opens the home page
    When The user scrolls down to the Hot Sellers section
    Then Each card should display an Add to Cart button
    And The Add to Cart button should be clickable

  Scenario: Guest user adds Breathe-Easy Tank to cart
    Given The user opens the home page
    When The user scrolls down to the Hot Sellers section
    And I hover on the card Breathe-Easy Tank
    And I select size M
    And I select color Purple
    And I click on Add to Cart button
    Then The product should be added to cart successfully
    And The cart icon should display number 1
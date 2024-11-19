Feature: Wishlist functionality for Guest User

  Scenario: Validate that Guest user can’t add any product to Wishlist
    Given the user opens the Magento website
    When the user scrolls down to the Hot Sellers section
    And the user hovers over any product card
    And the user clicks on the Add to Wishlist button
    Then the Guest user should be navigated to the login page

  Scenario: Validate that Logged in user can add any product to Wishlist
    Given the user opens the Magento website
    And the user is logged in
    When the user scrolls down to the Hot Sellers section
    And the user hovers over any product card
    And the user clicks on the Add to Wishlist button
    Then the user should be navigated to the My Wishlist page
    And the product should be added to the Wishlist

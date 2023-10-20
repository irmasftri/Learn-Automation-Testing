Feature: Add to Cart Functionality

  Scenario: User adds an item to the cart
    Given User is logged in
    When User clicks the add to cart button
    Then User should see the item in the cart
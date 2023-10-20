Feature: Checkout Process

  Scenario Outline: User checks out and completes the checkout process
    Given User has selected a product and added it to the cart
    When User proceeds to checkout
    And User enters billing information "<name>", "<address>", "<zip>"
    And User clicks the 'Continue' button
    And User verifies the order summary
    And User clicks the 'Finish' button
    Then User should see the order confirmation

    Examples:
      | name     | address                | zip   |
      | John Doe | 123 Main Street        | 12345 |

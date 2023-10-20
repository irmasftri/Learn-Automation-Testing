Feature: Login Functionality

  Scenario Outline: User login with valid credentials
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And User clicks the login button
    Then User should be redirected to the products page

    Examples:
      | username        | password       |
      | standard_user   | secret_sauce   |
      | problem_user    | secret_sauce   |
      | visual_user     | secret_sauce   |

  Scenario Outline: User login with invalid credentials
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And User clicks the login button
    Then User should see an error message

    Examples:
      | username        | password      |
      | invalidusername | invalidpass   |
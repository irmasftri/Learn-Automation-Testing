Feature: Sort Profuct Functionally

  Scenario Outline: User sorts products by sort option
    Given User is on the products page
    When User selects to sort by "<sort_option>"
    Then Products are sorted by "<sort_option>"

  Examples:
    | sort_option |
    | az          |
    | za          |
    | lohi        |
    | hilo        |
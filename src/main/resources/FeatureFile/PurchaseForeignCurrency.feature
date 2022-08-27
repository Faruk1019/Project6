Feature: Purchase Foreign Currency Functionality

  Scenario Outline: Purchase Foreign Currency
    Given User navigate to website
    And User enter valid username password
    When User navigate to Purchase Foreign Currency page
    And User add the currency "<amount>" information
    Then User should see currency success message

    Examples:
      | amount |
      | 1500   |
      | 3000   |

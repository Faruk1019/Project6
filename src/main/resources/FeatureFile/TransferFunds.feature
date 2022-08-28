Feature: Transfer Funds Functionality

  Scenario Outline: Transfer Funds
    Given User navigate to the website
    And User should Login
    When User navigate to Transfer Funds page
    And User select the "<amount>" of funds
    Then User should see transfer success message

    Examples:
      | amount |
      | 350    |
      | 500    |
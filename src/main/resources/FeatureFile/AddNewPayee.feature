Feature: Add New Payee Functionality

  Scenario Outline: Add new Payee
    Given User navigate to webapp website
    And User enter valid username and password
    When User navigate to Add New Payee page
    And User add the payee "<payeePerson>" information
    Then User should see success message


    Examples:
      | payeePerson |
      | Frank       |
      | Steven      |


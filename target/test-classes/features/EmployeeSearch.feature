Feature: Search an employee in HRMS system

  Background:
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on employee list option

  @sprint2 @regression
  Scenario: search an employee by id
    When user enters valid employee id
    And user clicks on search button
    Then user should be able to see employee details

  @sprint3 @regression
  Scenario: search an employee by name
    When user enters valid employee name
    And user clicks on search button
    Then user should be able to see employee details






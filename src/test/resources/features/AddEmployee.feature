Feature: Add Employees in HRMS

  @add @regression
  Scenario: Adding employee
    Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add Employee option
    When user enters firstName and middleName and lastName
    And user clicks on save button
    Then employee added successfully

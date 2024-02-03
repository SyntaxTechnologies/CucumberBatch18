Feature: Add Employees in HRMS

  Background:
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add Employee option

  @add @regression
  Scenario: Adding employee via hard coded data
    When user enters firstName and middleName and lastName
    And user clicks on save button
    Then employee added successfully

  @addemp @regression
  Scenario: Adding employee from feature file
    When user enters "aaron" "ms" and "sairam"
    And user clicks on save button
    Then employee added successfully

  @examples @regression
  Scenario Outline: Adding employees for data driven testing from feature file
    When user enters "<firstName>" and "<middleName>" and then "<lastName>"
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      |damla      |ms          |dayal     |
      |savera     |ms          |usman     |
      |sino       |ms          |karimi    |






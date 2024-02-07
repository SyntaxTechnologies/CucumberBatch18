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

   @datatable
    Scenario: Adding employees from data table
      When user add multiple employees from datatable and verify they are added
      |firstName|middleName|lastName|
      |sino     |ms        |SP      |
      |diana    |ms        |longone |
      |indira   |ms        |gandhi  |

    @excel
    Scenario: Adding employees from excel file
      When user adds multiple employees from excel and verify the employee has added







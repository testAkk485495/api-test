@API @Characters
Feature: Characters

  Scenario: Verify response schema is correct
    When get all items from "/characters" endpoint with pagination
    Then assert schema is correct

  Scenario Outline: Verify error is thrown
    When send GET to "<endpoint>" endpoint and with limit "<limit>" and offset "<offset>"
    Then assert error "<error>" is thrown
    Examples:
      | endpoint            | limit | offset | error                                          |
      | /characters         | 1000  | 0      | You may not request more than 100 items.       |
      | /characters         | test  | 0      | You must pass an integer limit greater than 0. |
      | /incorrect_endpoint | 10    | 0      | ResourceNotFound                               |


  Scenario:
    When send unauthorized GET request to "/characters" endpoint and with limit "10" and offset "0"
    Then assert error "You must provide a user key." is thrown


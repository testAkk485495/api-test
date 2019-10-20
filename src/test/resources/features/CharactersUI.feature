@UI @Characters
Feature: Characters

  Scenario:
    When get character by id "1010699"
    And get url from response
    And get number of comics from response
    Then assert number of comics on web page same as returned from api

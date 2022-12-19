@Regression
Feature: Pet Store

  @API_TC02
  Scenario Outline: TC01-API Test to validate the methods
    Given Get the pet store "<url>"
    When Add a new store in pet
    Then find the pet by ID
    Examples:
      | url                            |
      | https://petstore.swagger.io/#/ |



















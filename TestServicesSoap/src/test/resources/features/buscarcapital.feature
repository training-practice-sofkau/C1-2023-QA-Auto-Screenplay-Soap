Feature: Get the actual capital
  As a user of service getCities
  I want to get the capital of a country
  so that I can read the name of the capital

  @capital
  Scenario: List capital
    Given a user that wants to know the actual capital
    When the user sends the request to the api
    Then the user gets the capital
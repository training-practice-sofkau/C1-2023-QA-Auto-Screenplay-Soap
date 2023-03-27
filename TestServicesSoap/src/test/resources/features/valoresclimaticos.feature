Feature: Get weather values for a geographic location on a specific date
  AS
    api user
  I WANT
    validate the climatic values service
  SO THAT
    can see the results that I am looking for

  @Climate
  Scenario: get climate values for a location on a specific date
    Given I want to know the climatic values of a place
    When I send  request to the API
    Then I should get the expected result
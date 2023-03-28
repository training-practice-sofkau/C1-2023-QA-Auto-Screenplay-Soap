Feature: Get multiplication
  ME AS
    a user of the calculator
  I WANT
    to  do integer multiplication
  SO THAT
  I can do math calculations

  @LatLongList
  Scenario: Get multiplication
    Given a user needs to perform a multiplication
    When the user sends the request with two integers to the API
    Then he user gets the result of the multiplication
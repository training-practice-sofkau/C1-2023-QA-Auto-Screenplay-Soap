Feature: Get the international phone code of a country
  AS
    api user
  I WANT
    validate this service
  SO THAT
    can see the correct results of the telephone codes and learn them

  @phonecode
  Scenario: Obtain the telephone code of a country
    Given the user knows what the telephone code of a country
    When you send the request to the API
    Then you get the correct code
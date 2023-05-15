Feature: Get the temperature
  AS user
  I WANT TO convert celcius to fahrenheit
  SO THAT I can configure the bedroom temperature

  @temperatura
  Scenario Outline: Temperature
    Given a user that wants to convert celcius <grados> to fahrenheit
    When the user sends the request to the converion api
    Then the user gets the convertion result <Resultado>

    Examples:
      | grados | Resultado |
      | 30     | "86"      |
      | 15     | "59"      |
      | 0      | "32"      |
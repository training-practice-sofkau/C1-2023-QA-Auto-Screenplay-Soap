Feature: Currency of a country
  As
  a user of the currency of a country service
  I WANT TO
  be able to enter a country name
  SO THAT
  I can see the currency of that country

  Scenario Outline: List country currency
    Given the user wants to know the currency of a country
    When the user sends a request with the name "<country>" of a country
    Then the user gets the currency of that country "<currency>" and its ISO Code "<ISOCode>"
    Examples:
      | country | ISOCode | currency                          |
      | US      | USD     | Dollars                           |
      | CO      | COP     | Pesos                             |
      | GB      | GBP     | Great Brittain Pounds             |
      | Checho  |         | Country not found in the database |
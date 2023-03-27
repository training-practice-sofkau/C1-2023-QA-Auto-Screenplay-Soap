Feature: Get the actual exchange
  AS user
  I WANT TO exchange my money
  SO THAT I can travel to any country

  @divisas
  Scenario Outline: Change currency
    Given a user that wants to know the exchange of their money in the <Country> to <Ocountry> the amount of <Amount>
    When the user sends the request to the currenci api
    Then the user gets the money exchange at <Resultado>

    Examples:
      | Country | Ocountry | Amount | Resultado            |
      | "COP"   | "USD"    | 100000 | "21.348318811771914" |
      | "USD"   | "CRC"    | 10     | "5517.311798013553"  |
      | "CRC"   | "CNY"    | 500    | "6.2359938086745847" |

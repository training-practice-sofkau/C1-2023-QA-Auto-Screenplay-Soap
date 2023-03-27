Feature: Get the currency of the country
  AS
  user of the page of currencys
  I WANT TO
  know the currency of the countrys
  SO THAT
  use this information

  @Currency
  Scenario Outline: Get the currency of the country
    Given a user have the code of the country <code>
    When the user sends the code to the api
    Then the user gets the currency of the country <result>
    Examples:
      | code  | result                              |
      | "DE"  | "Euro"                              |
      | "COL" | "Pesos"                             |
      | "ES"  | "Euro"                              |
      | "aaa" | "Country not found in the database" |
Feature: Get words from the number
  AS
  user of the page data access
  I WANT TO
  get the number in words
  SO THAT
  have the clear bill

  @NumberToWords
  Scenario Outline: Get numbers in words
    Given a user have a <number> for the process
    When the user sends the number to the api
    Then the user gets the text of the number <text>
    Examples:
      | number | text                                   |
      | 78     | "seventy eight"                        |
      | 0      | "zero"                                 |
      | 1512   | "one thousand five hundred and twelve" |
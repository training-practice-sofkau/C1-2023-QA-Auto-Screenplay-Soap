Feature: Get a format text
  AS
  user of the page dataaccess
  I WANT TO
  get format text
  SO THAT
  use this text

  @FormatText
  Scenario Outline: Get text with format
    Given a user have a <text> that need format
    When the user sends the text to the api
    Then the user gets the text with correct format <correctText>
    Examples:
      | text                             | correctText                      |
      | "un texto sin formato"           | "Un Texto Sin Formato"           |
      | "TEXTO CON MUCHAS MAYUSCULAS"    | "Texto Con Muchas Mayusculas"    |
      | "un texte dans une autre langue" | "Un Texte Dans Une Autre Langue" |
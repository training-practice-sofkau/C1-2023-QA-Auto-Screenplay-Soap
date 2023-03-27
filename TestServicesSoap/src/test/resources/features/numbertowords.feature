Feature: Number to Words
  As
  a user of the Number Conversion Service
  I WANT TO
  be able to enter a number
  SO THAT
  see its value in words

  Scenario Outline: Convert number to words
    Given a user that wants to know the value of a number in words
    When the user sends a number "<number>" to the service
    Then the user gets the value of the number in words and a status code response <code>
    Examples:
      | number | code |
      | 2      | 200  |
      | 200    | 200  |
      | Hola   | 200  |

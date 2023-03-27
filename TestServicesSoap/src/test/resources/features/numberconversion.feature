Feature: convert numbers to letters
  ME AS
    a user of the number converter
  I WANT
  to do a numbers to letters conversion
  SO THAT
  I can do integrate this functionality in my application

  @ConvertToLetters
  Scenario: number to letters conversion
    Given a user who wants to convert numbers to letters
    When The user sends the request for a number to the API
    Then the user obtains the conversion of the number into letters
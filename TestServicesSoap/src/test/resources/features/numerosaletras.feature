Feature: Convert number to English letters
  AS
  user
  I WANT
  get
  SO THAT
  see numbers in letters

  @numbersinletters
  Scenario: numbers in letters
    Given a user that wants to know the numbers in letters
    When the user sends the request to the api Number Conversion Service
    Then the user gets the numbers in letteres
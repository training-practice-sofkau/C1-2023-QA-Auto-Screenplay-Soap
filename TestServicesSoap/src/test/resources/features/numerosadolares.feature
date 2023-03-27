Feature: Convert number to dollares
  AS
  user
  I WANT
  get
  SO THAT
  see numbers in dollares letters

  @numbersinletters
  Scenario: numbers in dollares
    Given a user that wants to know the numbers in dollares
    When the user sends the request to the api Number Conversion dollares
    Then the user gets the numbers in dollares
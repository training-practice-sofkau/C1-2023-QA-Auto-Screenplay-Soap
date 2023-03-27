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

 @numberoutline
  Scenario Outline: numbers in dollares letteres
    Given a user that wants to know the numbers <numbers> in dollares letters
    When the user sends the request whit numbers to the api  Conversion dollares
    Then the user gets the numbers  in dollares in letters <letters> and get status code <code>
    Examples:
      | numbers | letters        | code |
      | "4"     | "four dollars" | 200  |
      | "2"     | "two dollars"  | 200  |
      | "2"     | "twos dollars" | 200  |
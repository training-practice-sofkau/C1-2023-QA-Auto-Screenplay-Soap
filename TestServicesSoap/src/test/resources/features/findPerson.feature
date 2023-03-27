Feature: Find Person by ID
  AS
  user
  I WANT TO
  obtain information about a particular person based on their unique ID
  SO THAT
  I can access their relevant information.

  @findPerson
  Scenario Outline: Obtain Person Information by ID
    Given a user that wants to know the Person Information by ID
    When the user sends a request to get the person's information with the provided <id>
    Then the user should receive the person's information and the status <code>
    Examples:
      | id  | code |
      | "1" | 200  |

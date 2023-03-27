Feature: Convert different amounts and currencies
  As: An User of Xignite services
  I want: To convert different currencies
  So that

  @ConvertValue
  Scenario Outline: Convert currencies
    Given I have access to the Xignite API server
    When I try to convert from '<from>' currency, to '<to>' currency the amount '<amount>'
    Then I will see the response code <code>
    And I will see the result '<result>'
    Examples:
      | from | to  | amount | code | result |
      | COP  | USD | 50000  | 200  | 10.5   |
      | EUR  | USD | 20.5   | 200  | 22.1   |
      | CLP  | CAD | 30000  | 200  | 50.8   |
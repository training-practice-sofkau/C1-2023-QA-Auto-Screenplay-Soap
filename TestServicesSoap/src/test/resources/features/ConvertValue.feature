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
      | from     | to  | amount    | code | result  |
      | USD | COP | 10.5 | 200  | 49775.22 |
      | Canada   | EUR | 3/20/2023 | 200  | 1.4658  |
      | Europe   | USD | 2/25/2022 | 200  | 1.1216  |
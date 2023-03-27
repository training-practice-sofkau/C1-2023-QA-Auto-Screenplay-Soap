Feature: Obtain different conversion rates
  As: An User of Xignite services
  I want: To see different currency conversion rates
  So that

  @OfficialRate
  Scenario Outline: Conversion rates
    Given I have access to Xignite API server
    When I try to get the '<country>' currency to '<symbol>' as of the date '<date>'
    Then I will see a response code <code>
    And I will see the Open rate '<rate>'
    Examples:
      | country  | symbol | date      | code | rate    |
      | Colombia | USD    | 1/13/2018 | 200  | 2855.86 |
      | Canada   | EUR    | 3/20/2023 | 200  | 1.4658  |
      | Europe   | USD    | 2/25/2022 | 200  | 1.1216  |
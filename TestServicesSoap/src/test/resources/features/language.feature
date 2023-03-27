Feature: Get language name

  Scenario: Get language name successfully
    Given that the user has access to the LanguageName service
    When sends a SOAP request with the abbreviation of the desired language
    Then you should receive a successful response with the name of the corresponding language
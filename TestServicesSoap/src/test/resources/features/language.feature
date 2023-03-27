Feature: Get language name
  AS
  user of the service country info service
  I WANT TO
  get the name of a language
  SO THAT
  be able to identify the country of an unknown phone number

  Scenario: Get language name successfully
    Given that the user has access to the LanguageName service
    When sends a SOAP request with the abbreviation of the desired language
    Then you should receive a successful response with the name of the corresponding language
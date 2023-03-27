Feature: Get service version

  Scenario: Get service version successfully
    Given the admin has access to the version service
    When send a SOAP request to get the version of the service
    Then you should receive a successful response with the version of the service
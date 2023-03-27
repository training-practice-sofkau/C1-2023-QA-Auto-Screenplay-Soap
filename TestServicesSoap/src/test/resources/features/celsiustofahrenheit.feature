Feature: Celsius to Fahrenheit Conversion
 ME AS
    a user of the temperature converter
 I WANT
    to do a temperature conversion
 SO THAT
    I can do calculations in different systems of units

 @ConvertToFahrenheit
 Scenario: Celsius to Fahrenheit Conversion
   Given a user who wants to make a conversion to Fahrenheit
   When the user sends the request in Celsius to the api
   Then the user gets the conversion in Fahrenheit


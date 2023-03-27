Feature: GET del resultado de la conversion de temperaturas
  Como usuario del servicio de conversion de temperaturas
  Quiero obtener el resultado de la temperatura ingresada en fahrenheit a celcius
  Para poder verificar el correcto funcionamiento del servicio.

  @Temperatura
  Scenario: Obtener el resultado de una la conversion exitosamente
    Given un usuario quiere obtener el resultado de una conversion
    When envio una solicitud GET para obtener el resultado de una conversion de temperatura especificada
    Then el servicioooo responde con un estado HTTP 200 OK
    And debe devolver el resultado de la temperatura en el cuerpo de la respuesta
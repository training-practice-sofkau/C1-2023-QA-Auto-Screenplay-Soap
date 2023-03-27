Feature: GET de las coordenadas correspondientes al codigo postal
  Como usuario del servicio de coordenadas geograficas
  Quiero obtener las coordenadas correspondientes al codigo postal de una zona
  Para poder verificar el correcto funcionamiento del servicio.

  @ZipCode
  Scenario: Obtener coordenadas de un codigo postal existente
    Given un usuario quiere saber las coordenadas de un codigo postal
    When realizo una peticion POST con el codigo postal "35004"
    Then el servicio responde con un estado HTTP 200 OK
    And en el cuerpo de la respuesta obtengo las coordenadas correspondientes al codigo postal "35004"

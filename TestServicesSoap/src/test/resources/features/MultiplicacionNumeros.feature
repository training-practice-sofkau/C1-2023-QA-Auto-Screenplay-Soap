Feature: GET del resultado de una multiplicacion
  Como usuario del servicio calculadora
  Quiero obtener el resultado de la operacion matematica
  Para poder verificar el correcto funcionamiento del servicio.

  @Multiplicacion
  Scenario: Obtener el resultado de una multiplicación exitosamente
    Given un usuario quiere obtener el resultado de una multiplicacion
    When envio una solicitud GET para obtener el resultado de una multiplicacion especificada
    Then el servicio me responde con un estado HTTP 200 OK
    And debe devolver el resultado de la multiplicacion en el cuerpo de la respuesta

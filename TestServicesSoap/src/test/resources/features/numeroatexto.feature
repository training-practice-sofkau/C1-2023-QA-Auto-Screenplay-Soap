Feature: Conversión de números a texto con Data Access

  Scenario Outline: Conversión exitosa de un número a texto
    Given que tengo acceso al servicio web de Data Access para la conversion de numeros a texto
    When envio el numero "<numero>" al servicio
    Then deberia recibir el resultado "<resultado>"

    Examples:
      | numero | resultado                                                   |
      | 123    | ciento veintitres                                           |
      | 4567   | cuatro mil quinientos sesenta y siete                       |
      | 999999 | novecientos noventa y nueve mil novecientos noventa y nueve |
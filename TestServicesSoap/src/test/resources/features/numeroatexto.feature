Feature: Conversión de números a texto con Data Access

  Scenario Outline: Conversión exitosa de un número a texto
    Given que tengo acceso al servicio web de Data Access para la conversion de numeros a texto
    When envio el numero <numero> al servicio
    Then deberia recibir el resultado en texto "<resultado>"

    Examples:
      | numero | resultado                                                          |
      | 123    | hundred and twenty three                                           |
      | 4567   | four thousand five hundred and sixty seven                         |
      | 999999 | nine hundred and ninety nine thousand nine hundred and ninety nine |
Feature: Calculadora SOAP

  Como usuario del servicio SOAP
  Quiero poder realizar operaciones basicas de la calculadora
  Para poder conocer su resultado

  Scenario Outline: Sumar de dos numeros enteros
    Given Dado que tengo acceso al servicio SOAP calculadora
    And Tengo los numeros a sumar <num1>, <num2>
    When realizo la solicitud para sumar los numeros
    Then obtengo un codigo de estado exitoso "200"
    And El resultado correcto de la suma "<total>"

    Examples:
      | num1 | num2 | total |
      | 5    | 7    | 12    |
      | -10  | -4   | -14   |
      | 2    | 4    | 6     |

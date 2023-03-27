Feature: Suma de números con DNE Online

  Scenario Outline: Suma de numeros
    Given que tengo acceso al servicio web de DNE Online para la suma de numeros
    When envio los numeros <numero1> y <numero2> al servicio
    Then deberia recibir el resultado de la suma <resultado>

    Examples:
      | numero1 | numero2 | resultado |
      | 2       | 3       | 5         |
      | 4       | 6       | 10        |
      | 1       | 1       | 2         |

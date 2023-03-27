Feature: Suma de números con DNE Online

  Scenario Outline: Suma de numeros
  Given que tengo acceso al servicio web de DNE Online para la suma de numeros
  When envio los numeros "<numero1>" y "<numero2>" al servicio
  Then deberia recibir el resultado "<resultado>"

  Examples:
  | numero1 | numero2 | resultado |
  | 2       | 3       | 5         |
  | -2      | -3      | -5        |
  | 1.5     | 2.7     | 4.2       |

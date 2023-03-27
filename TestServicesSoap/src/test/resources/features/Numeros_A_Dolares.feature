Feature: Convertir numeros a dolares de forma escrita
  Como usuario del servicio de conversion de numeros
  Quiero obtener el resultado de la conversion del numero a dolares
  Para poder verificar el correcto funcionamiento del servicio.

  @NumerosDollars
  Scenario: Conversion de numero a dolares exitosa
    Given que el usuario quiere obtener el resultado de la conversion de numeros a dolares
    When ingreso el numero 70 en el campo de entrada
    Then el servicio responde con un estado 200 OK
    And el servicio muestra el resultado de la conversion en dolares "seventy doll"


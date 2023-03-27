Feature: Convertir numeros a dolares de forma escrita
  Como usuario del servicio de conversion de numeros
  Quiero obtener el resultado de la conversion del numero a dolares
  Para poder verificar el correcto funcionamiento del servicio.

  @NumerosDollars
  Scenario: Conversion de numero a dolares exitosa
    Given que el usuario quiere obtener el resultado de la conversion del numero a dolares
    When ingreso el numero 70 en el campo de entrada
    And el servicio responde con un estado 200 OK
    Then el servicio muestra el resultado de la conversion en dolares "seventy dollars"


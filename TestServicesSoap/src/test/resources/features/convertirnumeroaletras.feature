Feature: Convertir numero a letra
  Yo como administrador de un servicio SOAP de conversion de numeros a letras
  Quiero poder convertir numeros a su equivalente en letras de la cantidad de dollar
  Para validar esta funcionalidad

  @conversionCorrecta
  Scenario: Conversion exitosa de numero a letra
    Given el administrador quiere convertir un numero a su equivalente en letras
    When el administrador realiza la peticion de conversion del numero
    Then el administrador deberia ver el resultado de la conversion en letras correspondiente al numero proporcionado
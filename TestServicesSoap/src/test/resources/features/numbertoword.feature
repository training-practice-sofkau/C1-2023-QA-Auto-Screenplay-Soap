Feature: Conversor de numero SOAP
  Como usuario
  Quiero poder usar el conversor
  Para convertir un numero a su equivalente en palabras

  Scenario Outline: Obtener numero en letras
    Given Tengo un numero que quiero convertir <num1>
    When Hago una peticion POST que convierta el numero
    Then Se debe visualizar un codigo de estado "200" OK
    And El resultado debe ser la conversion correcta del numero en palabras <word>
    Examples:
      | num1  | word             |
      | "8"   | "eight"          |
      | "10"  | "ten"            |
      | "12"  | "twelve"         |
      | "2.5" | "two point five" |
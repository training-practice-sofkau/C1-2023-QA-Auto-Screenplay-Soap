Feature: Listar las monedas de cada país

  Como usuario del servicio SOAP
  Quiero poder listar las monedas de cada país
  Para poder conocer la moneda de un país determinado

  Scenario Outline: Listar las monedas de un país
    Given Dado que tengo acceso al servicio SOAP de Listar las monedas de cada pais
    And que he ingresado el codigo ISO de un pais valido "<CountryCode>"
    When realizo la solicitud de las monedas correspondientes
    Then obtengo un codigo de estado exitoso
    And El nombre de la moneda correspondiente "<name>"

    Examples:
      | CountryCode | name                    |
      | COP         | Pesos                   |
      | USD         | Dollars                 |
      | EUR         | Euro                    |
      | US          | Currency code NOT found |
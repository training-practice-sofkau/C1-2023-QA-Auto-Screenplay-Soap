Feature: Consultar numero de celular  de un país
  Yo como usuario  de la API de  CountryInfo
  Quiero consultar el numero de celular  de un país
  Para poder conocer el numero  de un país

  @NumeroCelularPorPais
  Scenario Outline:
    Given El servicio de Country Info Service SOAP esta disponible y estable
    When Envio el codigo ISO del pais <codigoISO> para consultar el numero de celular
    Then deberia obtener el  <numeroCelular>  y el <status> de la respuesta

    Examples:
      | codigoISO | numeroCelular                       | status |
      | "COL"     | "57"                                | 200    |
      | "MX"      | "52"                                | 200    |
      | "USA"     | "1"                                 | 200    |
      | "ARG"     | "54"                                | 200    |
      | "OO"      | "Country not found in the database" | 404    |
      | ""        | "Country not found in the database" | 404    |
      | "12"      | "Country not found in the database" | 404    |
      | "col"     | "57"                                | 200    |
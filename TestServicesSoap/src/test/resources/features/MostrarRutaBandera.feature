Feature: Consultar bandera de un país
  Yo como usuario  de la API de  CountryInfo
  Quiero consultar la bandera de un país
  Para poder conocer la bandera de un país

  Scenario Outline:
    Given El servicio de Country Info Service SOAP esta disponible
    When Envio el codigo ISO del pais <codigoISO>
    Then deberia obtener la <bandera>  y el <status> de la respuesta

    Examples:
      | codigoISO | bandera                                                               | status |
      | "COL"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/Colombia.jpg"  | 200    |
      | "col"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/Colombia.jpg"  | 200    |
      | "USA"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/USA.jpg"       | 200    |
      | "usa"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/USA.jpg"       | 200    |
      | "ARG"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/Argentina.jpg" | 200    |
      | "arg"     | "http://www.oorsprong.org/WebSamples.CountryInfo/Flags/Argentina.jpg" | 200    |
      | "OOO"     | "Country not found in the database"                                   | 404    |
      | ""        | "Country not found in the database"                                   | 404    |
      | "12"      | "Country not found in the database"                                   | 404    |


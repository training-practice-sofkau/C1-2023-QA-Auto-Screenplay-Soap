Feature: Consultar codigo ISO de un idioma
  Yo como usuario de la API de Conuntri Info Service SOAP
  Quiero consultar el codigo ISO de un idioma
  Para poder obtener el codigo ISO de un idioma en concreto

  @ConsultarCodigoISOIdioma
  Scenario Outline: Consultar idioma
    Given El servicio de Conuntri Info Service SOAP esta disponible
    When Consulto el codigo ISO del <idioma>
    Then deberia obtener el <codigoISO>  y el <status> de la respuesta

    Examples:
      | idioma    | status | codigoISO                  |
      | "Spanish" | 200    | "es"                       |
      | "English" | 200    | "eng"                      |
      | "French"  | 200    | "fr"                       |
      | "German"  | 200    | "de"                       |
      | "Pepe"    | 404    | "Language name not found!" |
      | "123"     | 404    | "Language name not found!" |


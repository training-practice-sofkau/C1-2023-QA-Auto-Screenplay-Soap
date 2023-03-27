Feature: Nombre del lenguaje segun el codigo
  yo como administrador que usa los servicios soap
  quiero relaizar peticiones al servicio post languaje name
  para obtener el nombre del lenguaje de un pais

  @Lenguaje
  Scenario Outline: Obtener nombre del lenguaje de un pais
    Given que estoy apuntando con un endpoint a la api SOAP de paises
    When envio la peticion post con el <codigo> del lenguaje de un pais
    Then recibo <code> de codigo de respuesta y el <nombre> del lenguaje
    Examples:
      | codigo        | nombre                        | code |
      | "abk"         | "Abkhazian"                   | 200  |
      | "DZD"         | "Language ISO Code not found!"| 200  |
      | "MGA"         | "Irish, Middle (900 - 1200)"  | 200  |
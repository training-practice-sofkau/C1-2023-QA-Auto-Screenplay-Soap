Feature: Nombre de la moneda
  yo como administrador que usa los servicios soap
  quiero relaizar peticiones al servicio post currency name
  para obtener la moneda de un pais

  @Moneda
  Scenario Outline: Obtener moneda de un pais
    Given que estoy apuntando con un endpoint a la api
    When envio la peticion post con el <codigo> de un pais
    Then recibo <code> de codigo de respuesta y el <nombre> de la moneda del pais
    Examples:
      | codigo        | nombre          | code |
      | "AFA"         | "Afghanis"      | 200  |
      | "DZD"         | "Algeria Dinars"| 200  |
      | "MGA"         | "Ariary"        | 200  |
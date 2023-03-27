Feature:Obtener nombre de la moneda
  yo como usuario de servicio de CurrencyName
  quiero obtener la informacion de la moneda de un pais
  para saber el nombre de la moneda un pais


  @Lenguaje
  Scenario: lista de el nombre de la moneda
    Given el usuario quiere saber el nombre de una moneda
    When el usuario envia una peticion a la api para obtener el nombre de la moneda
    Then el usuario como respuesta obtiene el nombre de la moneda

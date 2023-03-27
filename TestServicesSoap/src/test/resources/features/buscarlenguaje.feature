Feature:Obtener idioma de un pais
  yo como usuario de servicio de ListOfLanguages
  quiero obtener la informacion del idioma de un pais
  para saber el lenguaje de un pais


  @Lenguaje
  Scenario: lista de lenguajes
    Given el usuario quiere saber el lenguaje de un pais
    When el usuario envia una peticion solicitud a la api para obtener el idioma
    Then el usuario como respuesta obtiene el idioma del pais

Feature:Obtener el valor de un numero en letras
  yo como usuario de servicio de dataaccess
  quiero obtener el numero en letra
  para saber el valor textual


  @Lenguaje
  Scenario: listar el valor textual de un numero
    Given el usuario quiere saber el valor textual de un numero
    When el usuario envia una peticion a la api para obtener el valor textual de un numero
    Then debiria obtener como respuesta el valor de un numero en letras
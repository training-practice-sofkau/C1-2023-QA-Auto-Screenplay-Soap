Feature: filtrar pais por codigo
  AS
  usuario
  I WANT TO
  obtener
  SO THAT
  mirar el iso codigo por pais

@countryIsoCode
  Scenario: buscar pais por iso code
    Given como usuario quiero conocer el iso code asociado al pais
    When envio la peticion a la Api
    Then mostrara el pais con el iso code
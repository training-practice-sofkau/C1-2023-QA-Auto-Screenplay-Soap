Feature: Convirtiendo divisas
  Yo como usuario de
  Quiero convertir el valor de divisas a otras divisas
  para saber sus equivalentes en otras divisas

  @Divisa
  Scenario Outline: convirtiendo el valor de una divisa a otras
    Given estoy en el servicio de conversor de divisas
    When actualizo la informacion de una divisa <divisa>, <divisaCambio>
    Then me debe devolver el post actualizado <code>, <divisa>

    Examples:
      | divisa | divisaCambio | code |
      | "USD"  | "COP"        | 200  |
      | "EUR"  | "COP"        | 200  |
      | "MXN"  | "COP"        | 200  |
Feature: Conversión de divisas con CurrencySystem

  Scenario Outline: Conversión exitosa de una divisa a otra
  Given que tengo acceso al servicio web de CurrencySystem para la conversion de divisas
  When envio "<cantidad>" "<divisa_origen>" a "<divisa_destino>" al servicio
  Then deberia recibir el resultado "<resultado>"

  Examples:
  | cantidad | divisa_origen | divisa_destino | resultado   |
  | 100      | USD           | EUR            | 84.12       |
  | 1000     | MXN           | USD            | 51.28       |
  | 250      | CAD           | AUD            | 275.54      |


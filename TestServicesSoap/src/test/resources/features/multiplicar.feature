Feature: Multiplicar dos numero
  yo como administrador que usa los servicios soap
  quiero relaizar peticiones al servicio post de multiplicar
  para obtener el resultado

  @Multiplicar
  Scenario Outline: Obtener resultado de una multiplicacion
    Given que estoy apuntando con un endpoint a la api de la calculadora
    When envio la peticion post con el <num1> y el <num2>
    Then recibo <code> de codigo de respuesta y el <resultado> de la multiplicacion
    Examples:
      | num1        | num2    | code | resultado |
      | "5"         | "1"     | 200  | "5"       |


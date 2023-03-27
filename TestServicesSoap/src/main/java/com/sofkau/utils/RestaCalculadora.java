package com.sofkau.utils;

public enum RestaCalculadora {
    SOAP_RESTA_CALCULADORA_URL("http://www.dneonline.com/"),
    RESORUCE_RESTA_CALCULADORA("calculator.asmx"),
    BODY_PATH_RESTA_CALCULADORA("src/test/resources/soap/restacalculadora.xml");

    private String value;

    RestaCalculadora (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

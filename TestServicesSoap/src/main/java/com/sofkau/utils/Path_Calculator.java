package com.sofkau.utils;

public enum Path_Calculator {
    CALCULATOR_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_MULTIPLY("calculator.asmx"),
    BODY_PATH_MULTIPLY("src/test/resources/soap/filemultiply.xml");

    private String value;

    public String getValue() {
        return value;
    }

    Path_Calculator(String value) {
        this.value = value;
    }
}

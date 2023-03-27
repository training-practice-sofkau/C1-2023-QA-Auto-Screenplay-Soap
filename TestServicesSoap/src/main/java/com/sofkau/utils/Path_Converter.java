package com.sofkau.utils;

public enum Path_Converter {
    SOAP_COVERTER_BASE_URL("http://www.w3schools.com/"),
    RESOURCE_TEMP_CONVERTER("xml/tempconvert.asmx?op=CelsiusToFahrenheit"),
    BODY_PATH_FAHRENHEIT("src/test/resources/soap/tofahrenheit.xml");


    private String value;

    public String getValue() {
        return value;
    }

    Path_Converter(String value) {
        this.value = value;
    }
}

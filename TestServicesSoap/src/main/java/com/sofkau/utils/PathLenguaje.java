package com.sofkau.utils;

public enum PathLenguaje {
    SOAP_LENGUAJE_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_LENGUAJE("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_LENGUAJE("src/test/resources/soap/filelenguaje.xml");

    private String value;

    PathLenguaje(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

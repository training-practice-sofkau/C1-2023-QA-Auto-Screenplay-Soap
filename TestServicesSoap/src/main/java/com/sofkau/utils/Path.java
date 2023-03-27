package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    RESOURCE_MONEDA("websamples.countryinfo/CountryInfoService.wso"),
    BODY_MONEDA("src/test/resources/soap/filemoneda.xml"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_CALCULADORA_MULTIPLICAR("calculator.asmx"),
    BODY_MULTIPLICAR("src/test/resources/soap/multiplicar.xml"),
    RESOURCE_LENGUAJE("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_LENGUAJE("src/test/resources/soap/filelenguaje.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

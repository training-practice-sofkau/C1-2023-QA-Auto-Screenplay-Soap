package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    SOAP_DIVISAS_BASE_URL("http://fx.currencysystem.com/"),
    RESOURCE_CURRENCY("webservices/CurrencyServer4.asmx"),
    CURRENCY_BODY_PATH("src/test/resources/soap/filecurrency.xml"),
    SOAP_CALCULATOR_URL("http://www.dneonline.com/"),
    RESOURCE_SUM("calculator.asmx"),
    CALCULATOR_BODY_PATH("src/test/resources/soap/filecalculadora.xml"),
    SOAP_TEMPERATURE_BASE_URL("https://www.w3schools.com/"),
    RESOURCE_CELCIUS("xml/tempconvert.asmx"),
    TEMPERATURE_BODY_PATH("src/test/resources/soap/filegrados.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.sofkau.utils;

public enum PathLanguage {
    SOAP_LANGUAGE_URL("http://webservices.oorsprong.org/"),
    RESORUCE_LANGUAGE("websamples.countryinfo/CountryInfoService.wso?WSDL"),
    BODY_PATH_LANGUAGE("src/test/resources/soap/language.xml");

    private String value;

    PathLanguage (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

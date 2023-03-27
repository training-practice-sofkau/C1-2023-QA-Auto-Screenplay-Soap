package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    SOAP_CREDIT_CARD_VALIDATOR("https://secure.ftipgw.com/ArgoFire/"),
    RESOURCE_CREDIT_CARD("validate.asmx?WSDL"),

    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    CAPITAL_BODY_PATH("src/test/resources/soap/filecapital.xml"),
    CARD_BODY_PATH("src/test/resources/soap/filecreditcard.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

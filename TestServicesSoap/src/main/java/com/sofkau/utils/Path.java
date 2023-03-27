package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    SOAP_CREDIT_CARD_VALIDATOR("https://secure.ftipgw.com/ArgoFire/"),
    SOAP_BANK_BASE_URL("http://www.thomas-bayer.com/axis2/"),

    RESOURCE_CREDIT_CARD("validate.asmx?WSDL"),
    RESOURCE_BANK("services/BLZService"),

    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    CAPITAL_BODY_PATH("src/test/resources/soap/filecapital.xml"),
    CARD_BODY_PATH("src/test/resources/soap/filecreditcard.xml"),
    BANK_BODY_PATH("src/test/resources/soap/filebank.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

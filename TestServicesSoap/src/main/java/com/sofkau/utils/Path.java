package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    SOAP_SUMA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_SUMA("calculator.asmx"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    BODY_SUMA_PATH("src/test/resources/soap/filesuma.xml"),

    NUMBERTOWORD_BASE_URL("https://www.dataaccess.com"),
    RESORUCE_NUMBERTOWORD("/webservicesserver/numberconversion.wso"),
    BODY_NUMBERTOWORD_PATH("src/test/resources/soap/numbertoword.xml");
    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

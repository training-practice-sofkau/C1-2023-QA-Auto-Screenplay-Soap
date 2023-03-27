package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    XIGNITE_BASE_URL("http://globalcurrencies.xignite.com"),
    GLOBAL_CURRENCIES_RESOURCE("/xGlobalCurrencies.asmx"),
    BODY_OFFICIAL_RATE("src/test/resources/soap/fileOfficialRate.xml"),
    BODY_CONVERT_VALUE("src/test/resources/soap/fileConvertValue.xml");

    private final String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
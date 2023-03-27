package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    BODY_OFFICIAL_RATE("src/test/resources/soap/fileOfficialRate.xml"),
    XIGNITE_BASE_URL("http://globalcurrencies.xignite.com"),
    GLOBAL_CURRENCIES_RESOURCE("/xGlobalCurrencies.asmx");

    private final String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
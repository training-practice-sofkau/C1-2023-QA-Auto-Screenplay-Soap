package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    SOAP_CAMEL_CASE_BASE_URL("https://www.dataaccess.com/webservicesserver/"),
    RESOURCE_CAMEL_CASE("TextCasing.wso"),
    BODY_CAMEL_CASE_PATH("src/test/resources/soap/filecamelcase.xml"),
    SOAP_CURRECNCY_BASE_URL("http://webservices.oorsprong.org/"),
    SOAP_CURRENCY_RESOURCE("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_CURRENCY("src/test/resources/soap/filecurrencycountry.xml")
    ;

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

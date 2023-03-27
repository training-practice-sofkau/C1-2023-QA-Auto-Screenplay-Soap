package com.sofkau.utils;

public enum PathIsoCode {
    SOAP_ISOCODE_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_ISOCODE("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_ISOCODE("src/test/resources/soap/filecountryIsoCode.xml");

    private String value;

    PathIsoCode (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.sofkau.utils;

public enum PathNumberConversion {

    SOAP_NUMBER_CONVERSION_BASE_URL("https://www.dataaccess.com"),
    RESOURCE_NUMBER_CONVERSION("/webservicesserver/numberconversion.wso"),
    BODY_PATH_NUMBER_CONVERSION("src/test/resources/soap/filenumberconversion.xml");

    private String value;

    PathNumberConversion (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

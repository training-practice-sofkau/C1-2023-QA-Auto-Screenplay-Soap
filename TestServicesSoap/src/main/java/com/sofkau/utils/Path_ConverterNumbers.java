package com.sofkau.utils;

public enum Path_ConverterNumbers {
    SOAP_NUMBER_CONVERSION_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMBER_TO_WORDS("webservicesserver/numberconversion.wso"),
    BODY_PATH_NUMBER("src/test/resources/soap/filenumbertowords.xml");

    private String value;

    Path_ConverterNumbers(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

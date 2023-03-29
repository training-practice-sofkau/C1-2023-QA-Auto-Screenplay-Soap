package com.sofkau.utils;

public enum PathFindByName {
    SOAP_FIND_BY_NAME_BASE_URL("http://www.crcind.com/"),
    RESOURCE_FIND_BY_NAME("csp/samples/SOAP.Demo.cls"),
    BODY_FIND_BY_NAME_PATH("src/test/resources/soap/findByName.xml");

    private String value;

    PathFindByName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

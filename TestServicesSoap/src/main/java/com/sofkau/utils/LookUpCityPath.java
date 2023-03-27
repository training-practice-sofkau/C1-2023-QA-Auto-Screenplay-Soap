package com.sofkau.utils;

public enum LookUpCityPath {

    SOAP_LOOK_UP_CITY_BASE_URL("http://www.crcind.com/"),
    RESOURCE_LOOK_UP_CITY("csp/samples/SOAP.Demo.cls"),
    BODY_LOOK_UP_CITY_PATH("src/test/resources/soap/lookUpCity.xml");

    private String value;

    LookUpCityPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

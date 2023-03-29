package com.sofkau.utils;

public enum PathFindPerson {

    SOAP_FIND_PERSON_BASE_URL("http://www.crcind.com/"),
    RESOURCE_FIND_PERSON("csp/samples/SOAP.Demo.cls"),
    BODY_FIND_PERSON_PATH("src/test/resources/soap/findperson.xml");

    private String value;

    PathFindPerson(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

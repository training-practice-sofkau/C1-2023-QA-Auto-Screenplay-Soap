package com.sofkau.utils;

public enum PathVersion {
    SOAP_VERSION_URL("http://fx.cloanto.com/webservices/"),
    RESORUCE_VERSION("CurrencyServer.asmx?WSDL"),
    BODY_PATH_VERSION("src/test/resources/soap/version.xml");

    private String value;

    PathVersion (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH_capital("src/test/resources/soap/filecapital.xml"),
    SOAP_SUM_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_SUM("calculator.asmx?wsdl"),
    BODY_PATH_SUM("src/test/resources/soap/filesum.xml"),
    BODY_CONTRIES("http://eaf.ema.europa.eu/"),
    BODY_PATH_CONTRIES("eaf/services/EutctService?wsdl");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

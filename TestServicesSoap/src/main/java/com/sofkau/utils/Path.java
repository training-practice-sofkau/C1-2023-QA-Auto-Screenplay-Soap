package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    SOAP_ZIPCODE_BASE_URL("https://graphical.weather.gov:443/"),
    RESOURCE_ZIPCODE("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_ZIP_PATH("src/test/resources/soap/filezipcode.xml"),
    SOAP_NUMEROS_DOLARES_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMEROS_DOLARES("webservicesserver/numberconversion.wso"),
    BODY_NUMEROS_DOLARES_PATH("src/test/resources/soap/fileNumerosDolars.xml"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_CALCULADORA("calculator.asmx"),
    BODY_CALCULADORA_PATH("src/test/resources/soap/fileCalculadora.xml");


    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

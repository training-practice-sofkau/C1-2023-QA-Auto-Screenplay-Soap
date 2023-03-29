package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    SOAP_DIVISA_BASE_URL("http://fx.currencysystem.com/"),
    RESOURCE_DIVISA("webservices/CurrencyServer4.asmx"),
    BODY_PATH1("src/test/resources/soap/filedivisas.xml"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_CALCULADORA("calculator.asmx"),
    BODY_PATH2("src/test/resources/soap/filecalculadora.xml"),
    SOAP_NUMTEX_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMTEX("webservicesserver/numberconversion.wso"),
    BODY_PATH3("src/test/resources/soap/filenumeroatexto.xml");
    private String value;
    Path(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

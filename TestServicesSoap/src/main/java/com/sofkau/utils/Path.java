package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    RESOURCE_ISO_LENGUAJE_CODE("websamples.countryinfo/CountryInfoService.wso"),
    RESOURCE_BANDERA_ISO_CODE("websamples.countryinfo/CountryInfoService.wso"),

    BODY_PATH_ISO_CODE("src/test/resources/soap/filecodigoISOidioma.xml"),

    BODY_PATH_ISO_BANDERA("src/test/resources/soap/filecodigoISObandera.xml"),

    BODY_PATH_ISO_NUMERO_CELULAR  ("src/test/resources/soap/filecodigoISOnumerocelular.xml"),
    BODY_PATH("src/test/resources/soap/filecapital.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.sofkau.utils;

public enum Path {
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soap/filecapital.xml"),
    SOAP_CODIGOTEL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CODIGOTELEFONICO("websamples.countryinfo/CountryInfoService.wso"),
    BODYCODIGOTEL_PATH("src/test/resources/soap/filecodigotel.xml"),
    SOAP_CONVERTIDOR_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_CONVERTIDOR("webservicesserver/numberconversion.wso"),
    BODY_CONVERTIDOR_PATH   ("src/test/resources/soap/fileconvertirnumero.xml"),
    SOAP_CLIMA_BASE_URL("https://graphical.weather.gov/"),
    RESOURCE_CLIMA("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_CLIMA_PATH("src/test/resources/soap/filevaloresclimaticos.xml");

    private String value;

    Path(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

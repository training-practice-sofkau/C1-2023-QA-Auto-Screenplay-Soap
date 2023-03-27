package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "");
        return headersCollection;
    }
    public Map<String, Object> getHeadersZipCode(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl#LatLonListZipCode");
        return headersCollection;
    }
    public Map<String, Object> getHeadersNumerosDolares(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "");
        return headersCollection;
    }
    public Map<String, Object> getHeadersCalculadora(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Multiply");
        return headersCollection;
    }



    public static Headers headers(){
        return new Headers();
    }
}

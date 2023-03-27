package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class HeadersGrados{
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type","text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction","https://www.w3schools.com/xml/CelsiusToFahrenheit");
        return headersCollection;
    }

    public static HeadersGrados headersGrados(){
        return new HeadersGrados();
    }
}

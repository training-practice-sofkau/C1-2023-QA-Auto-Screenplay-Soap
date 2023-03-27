package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class HeadersResta {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Subtract");
        return headersCollection;
    }

    public static HeadersResta headersResta(){
        return new HeadersResta();
    }
}
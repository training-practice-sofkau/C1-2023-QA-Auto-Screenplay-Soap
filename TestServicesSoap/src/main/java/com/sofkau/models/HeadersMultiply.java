package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class HeadersMultiply {
    private Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Multiply");
        return headersCollection;
    }

    public static HeadersMultiply headersMultiply(){return new HeadersMultiply();}

}

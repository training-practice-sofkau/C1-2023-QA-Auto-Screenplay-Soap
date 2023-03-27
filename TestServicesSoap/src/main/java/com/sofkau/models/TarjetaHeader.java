package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class TarjetaHeader {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCollection(){
        this.headersCollection.put("Content-Type", "application/soap+xml;charset=utf-8;action=\"http://localhost/SmartPayments/GetCardType");
        //this.headersCollection.put("SOAPAction", "");
        return headersCollection;
    }

    public static TarjetaHeader tarjetaHeaders(){
        return new TarjetaHeader();
    }
}

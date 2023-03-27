package com.sofkau.models;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, Object> headersCol = new HashMap<>();

    public Map<String, Object> getHeadersCol() {
        this.headersCol.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCol.put("SOAPAction", "");
        return headersCol;
    }

    public Map<String, Object> getHeaders(String headers) {
        this.headersCol.put("Content-Type", headers);
        return headersCol;
    }

    public static Headers headers() {
        return new Headers();
    }
}
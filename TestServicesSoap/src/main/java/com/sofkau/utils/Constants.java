package com.sofkau.utils;

public enum Constants {
    ACTOR("Xignite user"),
    TOKEN("6DB4187689F74AEC9ABDE3A933FC3DDF"),
    TYPE("application/soap+xml"),
    CODE("charset=UTF-8"),
    ACTION_OFFICIAL_RATE("action=\"http://www.xignite.com/services/GetOfficialRate\""),
    ACTION_CONVERT_VALUE("action=\"http://www.xignite.com/services/ConvertRealTimeValue\"");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
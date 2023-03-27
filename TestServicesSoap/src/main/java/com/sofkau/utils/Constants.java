package com.sofkau.utils;

public enum Constants {
    ACTOR("Xignite user"),
    TOKEN("6DB4187689F74AEC9ABDE3A933FC3DDF");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
package org.example.constant;

public class Params {
    private final String key;
    private final String value;
    private final ParamType type;

    public Params(String key, String value, ParamType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public ParamType getType() {
        return type;
    }

    public enum ParamType {
        PARAM, HEADER, BODY
    }
}
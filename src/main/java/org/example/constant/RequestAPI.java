package org.example.constant;

public enum RequestAPI {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE");

    private final String formatReq;

    RequestAPI(String formatReq) {
        this.formatReq = formatReq;
    }

    public String getFormatReq() {
        return formatReq;
    }
}

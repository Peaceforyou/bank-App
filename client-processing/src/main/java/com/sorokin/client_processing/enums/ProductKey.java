package com.sorokin.client_processing.enums;

public enum ProductKey {
    DC("client_products"),
    CC("client_products"),
    NS("client_products"),
    PENS("client_products"),

    IPO("client_credit_products"),
    PC("client_credit_products"),
    AC("client_credit_products"),

    INS("client_products"),
    BS("client_products");

    private final String topic;

    ProductKey(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}

package com.example.controller.pojo;

/**
 * Created by deadlock on 27/1/17.
 */
public enum ResponseStatus {
    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    ERROR(-1, "error");

    private final int value;

    private final String reasonPhrase;

    ResponseStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}

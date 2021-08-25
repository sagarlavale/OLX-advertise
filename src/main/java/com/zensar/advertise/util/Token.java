package com.zensar.advertise.util;

public enum Token {

    TOKEN_EXPIRED("2"),INVALID_USER("1"),TOKEN_EMPTY("3"),INVALID_SIGNATURE("4");

    String value;

    public String getValue() {
        return value;
    }

    Token(String value) {
        this.value = value;
    }
}

package com.google.hashcode.entity;

public enum Position {
    HORIZONTAL("H"), VERTICAL("V");

    private final String type;

    Position(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

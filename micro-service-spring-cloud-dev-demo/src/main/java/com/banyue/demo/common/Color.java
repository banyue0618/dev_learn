package com.banyue.demo.common;

public enum Color {
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF"),
    YELLOW("#FFFF00"),
    BLACK("#000000"),
    WHITE("#FFFFFF");

    private final String hexValue;

    Color(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return hexValue;
    }
}


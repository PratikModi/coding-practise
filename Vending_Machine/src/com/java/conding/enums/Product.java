package com.java.conding.enums;

/**
 * Created by Pratik1 on 09-03-2020.
 */
public enum Product {
    COKE(25),PEPSI(35),SODA(45);

    private long value;

    Product(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}

package com.java.conding.enums;

/**
 * Created by Pratik1 on 09-03-2020.
 */
public enum Coins{
    PENNY(1),NICKEL(5),DIME(10),QUATER(25);

    private int value;

    Coins(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

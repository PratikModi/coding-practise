package com.java.coding.interviews.practise.coinbase.connect4.model;

public class Player {

    private String name;
    private Stone stone;

    public Player(String name, Stone stone) {
        this.name = name;
        this.stone = stone;
    }

    public String getName() {
        return name;
    }

    public Stone getStone() {
        return stone;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                '}';
    }
}

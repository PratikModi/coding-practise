package com.java.coding.interviews.practise.coinbase.connect4.model;

public class Move {
    private Position position;
    private Player player;

    public Move(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    @Override
    public String toString() {
        return "Move{" +
                "position=" + position +
                ", player=" + player +
                '}';
    }
}

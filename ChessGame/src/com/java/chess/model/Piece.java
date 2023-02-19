package com.java.chess.model;

public abstract class Piece {

    private boolean killed;
    private boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract boolean canMove(Board board, Box start, Box end);
}

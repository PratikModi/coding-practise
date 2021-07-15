package com.java.chess.model;

public class Bishop extends Piece{
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return true;
    }
}

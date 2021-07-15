package com.java.chess.model;

public class Knight extends Piece{

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        if(end.getPiece().isWhite() == this.isWhite())
            return false;
        int X = Math.abs(start.getX()-end.getX());
        int Y = Math.abs(start.getY()-end.getY());
        return (X*Y)==2;
    }
}

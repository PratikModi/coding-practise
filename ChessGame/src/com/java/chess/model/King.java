package com.java.chess.model;

public class King extends Piece{

    private boolean castlingDone = false;

    public King(boolean white) {
        super(white);
    }

    public boolean isCastlingDone() {
        return this.castlingDone == true;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    private boolean isValidCastling(Board board, Box start, Box end) {

        if(this.isCastlingDone()) {
            return false;
        }

        // check for the white king castling
        if(this.isWhite()
                && start.getX() == 0 && start.getY() == 4 && end.getY() == 0) {
            // confirm if white king moved to the correct ending box
            if (Math.abs(end.getY() - start.getY()) == 2) {
                // check if there the Rook is in the correct position
                // check if there is no piece between Rook and the King
                // check if the King or the Rook has not moved before
                // check if this move will not result in king being attacked
                //...
                this.setCastlingDone(true);
                return true;
            }
        } else {
            // check for the black king castling
            this.setCastlingDone(true);
            return true;
        }

        return false;
    }

    public boolean isCastlingMove(Box start, Box end) {
        // check if the starting and ending position are correct
        return false;
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        if(end.getPiece().isWhite()==this.isWhite())
            return false;
        int X = Math.abs(start.getX()-end.getX());
        int Y = Math.abs(start.getY()-end.getY());
        if(X+Y==1)
            return true;
        return false;
    }


}

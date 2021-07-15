package com.java;

import com.java.chess.enums.GameStatus;
import com.java.chess.model.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private Player[] players;
    private Player currentPlayer;
    private GameStatus status;
    private List<Move> moves;

    public Game(Board board) {
        this.board=board;
        board.initialize();
        players = new Player[2];
        moves = new ArrayList<>();
    }

    private void initialize(Player P1, Player P2){
        if(P1.isWhiteSide())
            this.currentPlayer=P1;
        else
            this.currentPlayer=P2;
        moves.clear();
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Box startBox = board.getBox(startX, startY);
        Box endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            return false;
        }

        // valid player
        if (player != currentPlayer) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        // valid move?
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())){
            return false;
        }

        // kill?
        Piece destPiece = move.getStart().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

        // castling?
        if (sourcePiece != null && sourcePiece instanceof King
                && ((King) sourcePiece).isCastlingMove(move.getStart(),move.getEnd())) {
            move.setCastlingMove(true);
        }

        // store the move
        moves.add(move);

        // move piece from the stat box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if (destPiece != null && destPiece instanceof King) {
            if(player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        // set the current turn to the other player
        if(this.currentPlayer == players[0]) {
            this.currentPlayer = players[1];
        } else {
            this.currentPlayer = players[0];
        }

        return true;
    }


    public static void main(String[] args) {

    }

}

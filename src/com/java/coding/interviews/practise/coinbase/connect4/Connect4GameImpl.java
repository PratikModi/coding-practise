package com.java.coding.interviews.practise.coinbase.connect4;

import com.java.coding.interviews.practise.coinbase.connect4.model.Board;
import com.java.coding.interviews.practise.coinbase.connect4.model.Move;
import com.java.coding.interviews.practise.coinbase.connect4.model.Player;
import com.java.coding.interviews.practise.coinbase.connect4.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Connect4GameImpl implements Connect4Game{

    private Board board;
    private int currentPlayer;
    private Player[] players;
    private Connect4WinChecker winChecker;
    private List<Move> moves;
    private Scanner scanner;

    public Connect4GameImpl(int height, int weight, Player[] players) {
        this.scanner = new Scanner(System.in);
        this.board = new Board(height,weight);
        this.players = players;
        this.moves = new ArrayList<>();
        this.currentPlayer=0;
        this.winChecker = new Connect4WinChecker(board);
    }

    @Override
    public Player Play(){
        Player winner=null;
        while (winner==null){
            winner = makeMove();
            currentPlayer = (currentPlayer+1)%2;
        }
        board.show();
        return winner;
    }

    @Override
    public Player makeMove() {
        board.show();
        int playersColumn = getUserInput();
        Position pos = board.putStoneAt(playersColumn, players[currentPlayer].getStone());
        moves.add(new Move(pos,players[currentPlayer]));
        return winChecker.isWinner(pos) ? players[currentPlayer] : null;
    }

    private int getUserInput() {
        System.out.print(String.format("%s, please select a column (1 - %d):", players[currentPlayer].getName(),board.getWidth()));
        int playersColumn = scanner.nextInt() - 1;
        return playersColumn;
    }

}

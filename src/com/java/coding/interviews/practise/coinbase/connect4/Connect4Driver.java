package com.java.coding.interviews.practise.coinbase.connect4;

import com.java.coding.interviews.practise.coinbase.connect4.model.Player;
import com.java.coding.interviews.practise.coinbase.connect4.model.Stone;

public class Connect4Driver {

    public static void main(String[] args) {
        int height =7;
        int width=6;
        Player P1 = new Player("P1", Stone.RED);
        Player P2 = new Player("P2", Stone.BLUE);

        Connect4Game game = new Connect4GameImpl(height,width,new Player[]{P1,P2});
        Player winner = game.Play();
        System.out.println(winner);
    }

}

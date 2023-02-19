package com.java.chess.model;

public class Board {
    private Box[][] boxes;

    public Board() {
        boxes = new Box[8][8];
    }

    public void initialize(){
        boxes[0][0] = new Box(0,0,new Rook(true));
        boxes[0][1] = new Box(0,1,new Knight(true));
        boxes[0][2] = new Box(0,2,new Bishop(true));
        boxes[0][2] = new Box(0,3,new King(true));
        /*
        ........................................................
        ........................................................
        ........................................................
         */
        boxes[7][0] = new Box(7,0,new Rook(false));
        boxes[7][1] = new Box(7,1,new Knight(false));
        boxes[7][2] = new Box(7,2,new Bishop(false));
        boxes[7][2] = new Box(7,3,new King(false));
    }

    public Box getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new RuntimeException("Index out of bound");
        }
        return boxes[x][y];
    }
}

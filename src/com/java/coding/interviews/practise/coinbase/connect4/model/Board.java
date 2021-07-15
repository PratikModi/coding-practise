package com.java.coding.interviews.practise.coinbase.connect4.model;

import java.util.Arrays;

public class Board {
    int height;
    int width;
    Stone[][] stones;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        stones = new Stone[height][width];
        for(int i=0;i<height;i++){
            Arrays.fill(stones[i],Stone.NONE);
        }
    }

    public Stone stoneAt(Position position){
        if(isValidPosition(position))
            return stones[position.getX()][position.getY()];
        return null;
    }


    public Position putStoneAt(int column, Stone stone){
        int i;
        Position position=null;
        for(i=height-1;i>=0;i--){
            position = new Position(i,column);
            if(isValidPosition(position) && stones[i][column]==Stone.NONE){
                setStoneAt(position,stone);
                break;
            }
        }
        return position;
    }

    private void setStoneAt(Position position, Stone stone){
        stones[position.getX()][position.getY()]=stone;
    }


    public boolean isValidPosition(Position position){
        return position.getX()>=0 && position.getX()<height && position.getY()>=0 && position.getY()<width;
    }

    public void show(){
        for(int i=0;i<height;i++){
            for(int j=0;j<stones[i].length;j++){
                System.out.print(stones[i][j]+" ");
            }
            System.out.println();
        }
    }

}

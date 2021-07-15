package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Pratik1 on 08-04-2020.
 */
public class NQueenProblem {
    private static int noOfQueen;
    private static int[][] board;

    public NQueenProblem(int noOfQueen) {
        this.noOfQueen = noOfQueen;
        board = new int[noOfQueen][noOfQueen];
    }

    public static void main(String[] args) {
        NQueenProblem problem = new NQueenProblem(8);
        placeQueen(0);
        printBoard();
        ArrayList<String> list = (ArrayList)Arrays.stream(new String[]{"abc"}).collect(Collectors.toList());
    }

    public static void printBoard(){
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public static boolean isSafe(int row, int col){
        int i,j;
        for(i=0;i<col;i++){
            if(board[row][i]==1)
                return false;
        }
        //Left Up
        for(i=row,j=col;i>=0&&j>=0;i--,j--){
            if(board[i][j]==1)
                return false;
        }

        //Left Down
        for(i=row,j=col;i<noOfQueen&&j>=0;i++,j--){
            if(board[i][j]==1)
                return false;
        }
        return true;
    }

    private static boolean placeQueen(int column){
        if(column==noOfQueen)
            return true;
        for(int row=0;row<noOfQueen;row++){
            if(isSafe(row,column)) {
                board[row][column] = 1;
                if (placeQueen(column + 1))
                    return true;
                board[row][column]=0;
            }
        }
        return false;
    }

}

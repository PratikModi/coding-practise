package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 27-06-2020.
 */

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

 A sudoku solution must satisfy all of the following rules:

 Each of the digits 1-9 must occur exactly once in each row.
 Each of the digits 1-9 must occur exactly once in each column.
 Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 Empty cells are indicated by the character '.'.

 A sudoku puzzle...
 https://leetcode.com/problems/sudoku-solver/

 ...and its solution numbers marked in red.

 Note:

 The given board contain only digits 1-9 and the character '.'.
 You may assume that the given Sudoku puzzle will have a single unique solution.
 The given board size is always 9x9.
 */
public class SudokuSolverProblem {
    public static void main(String[] args) {
        char[][] board = new char[][]
                {{'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'}
                };
        resolveSudoku(board);
        Arrays.stream(board).forEach(e-> System.out.println(Arrays.toString(e)));
    }
    public static void resolveSudoku(char[][] board){
        if(board==null | board.length<9 || board[0].length<9)
            return;
        boolean[][] row = new boolean[9][9];
        boolean[][] column = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!='.'){
                    int value = board[i][j]-'1';
                    int boxId = 3*(i/3)+(j/3);
                    row[i][value]=true;
                    column[j][value]=true;
                    box[boxId][value]=true;
                }
            }
        }
        backTracking(board,0,0,row,column,box);
    }

    private static boolean backTracking(char[][] board,int rowId,int columnId,boolean[][] row,boolean[][] column,boolean[][] box){
        if(rowId==9 && columnId==0)
            return true;
        int nextRow = columnId==8?rowId+1:rowId;
        int nextColumn = columnId==8?0:columnId+1;
        if(board[rowId][columnId]!='.'){
            return backTracking(board,nextRow,nextColumn,row,column,box);
        }else{
            for(int i=1;i<=9;i++){
                if(!row[rowId][i-1] && !column[columnId][i-1] && !box[3*(rowId/3)+(columnId/3)][i-1]){
                    board[rowId][columnId]=(char)(i+'0');
                    row[rowId][i-1]=true;
                    column[columnId][i-1]=true;
                    box[3*(rowId/3)+(columnId/3)][i-1]=true;
                    if(backTracking(board,nextRow,nextColumn,row,column,box))
                        return true;
                    board[rowId][columnId]='.';
                    row[rowId][i-1]=false;
                    column[columnId][i-1]=false;
                    box[3*(rowId/3)+(columnId/3)][i-1]=false;
                }

            }
        }
        return false;
    }

}

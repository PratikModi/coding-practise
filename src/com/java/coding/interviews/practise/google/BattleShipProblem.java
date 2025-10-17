package com.java.coding.interviews.practise.google;

/**
 * 419. Battleships in a Board
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words,
 * they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column),
 * where k can be of any size. At least one horizontal or vertical cell separates between two battleships
 * (i.e., there are no adjacent battleships).
 *
 * ✅ Optimal Solution (Mathematical / Counting Logic)
 *
 * We only need to count the starting cell of each battleship — i.e., the top-left-most cell of a ship.
 *
 * A cell (i,j) is the start of a new battleship if:
 * 	•	It’s 'X', and
 * 	•	There is no ‘X’ above it (board[i-1][j] != 'X'), and
 * 	•	There is no ‘X’ to the left of it (board[i][j-1] != 'X').
 *
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * Example 2:
 *
 * Input: board = [["."]]
 * Output: 0
 *
 * 🧮 Complexity
 * Time
 * O(M×N) — single pass
 * Space
 * O(1) — no extra memory
 */
public class BattleShipProblem {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println(countBattleships(board));
        board = new char[][]{{'.'}};
        System.out.println(countBattleships(board));
    }

    public static int countBattleships(char[][] board) {
        int count=0;
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='.') continue;
                // If the top cell is 'X', this is not a new ship
                if(i>0 && board[i-1][j]=='X') continue;
                // If the left cell is 'X', this is not a new ship
                if(j>0 && board[i][j-1]=='X') continue;
                // Otherwise, this 'X' starts a new battleship
                count++;
            }
        }
        return count;
    }

}

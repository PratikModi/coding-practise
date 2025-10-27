package com.java.coding.interviews.practise.wise;

/**
 * 348. Design Tic-Tac-Toe
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board.
 * The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
 * 0 if there is no winner after the move,
 * 1 if player 1 is the winner after the move, or
 * 2 if player 2 is the winner after the move.
 *
 * ❌ Naive Approach (O(n²))
 * Maintain a 2D board and after each move:
 * Mark board[row][col] = player
 * Check entire row, column, and diagonals for win.
 * Problem:
 * Every move() would take O(n) → for n=1000, too slow (10⁶ operations).
 *
 * ✅ Optimized Approach (O(1))
 * Instead of scanning the entire board, we track counts per row, column, and diagonal for each player.
 * Idea:
 * Each cell contributes +1 if Player 1 plays, and –1 if Player 2 plays.
 * If any row/col/diag has an absolute sum = n, that player wins.
 *
 * 🧩 Time and Space Complexity
 * Operation	Time	Space
 * move()	    O(1)	 O(n)
 *
 * 👉 The diagonal and anti-diagonal are special lines that cut across the square board:
 * Diagonal = top-left → bottom-right
 * Anti-diagonal = top-right → bottom-left
 *
 * 🔹 1️⃣ Main Diagonal
 * Cells that go from top-left to bottom-right:
 * (0,0), (1,1), (2,2)
 * 👉 Notice a pattern:
 * For all of them, row index == column index.
 *
 * if (row == col)
 *
 * 🔹 2️⃣ Anti-Diagonal
 * Cells that go from top-right to bottom-left:
 * (0,2), (1,1), (2,0)
 * 👉 The pattern here is different:
 * For each cell,
 * row + col = n - 1
 *
 * Example 1:
 *
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 */
public class TicTacToeProblem {

    int[] rows;
    int[] column;
    int diagonal;
    int anyDiagonal;
    int n;

    public TicTacToeProblem(int n) {
        this.n=n;
        this.rows = new int[n];
        this.column = new int[n];
        this.diagonal=0;
        this.anyDiagonal=0;
    }

    public int move(int row, int col, int player) {
        int add = player==1?1:-1;

        rows[row]+=add;
        column[col]+=add;

        if(row==col){
            diagonal+=add;
        }
        if(row+col==n-1){
            anyDiagonal+=add;
        }
        if(Math.abs(rows[row])==n ||
            Math.abs(column[col])==n ||
                Math.abs(diagonal)==n ||
                Math.abs(anyDiagonal)==n
        ){
            return player;
        }

        return 0;
    }

    /**
     * Example 1:
     *
     * Input
     * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
     * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
     * Output
     * [null, 0, 0, 0, 0, 0, 0, 1]
     * @param args
     */

    public static void main(String[] args) {
        TicTacToeProblem problem = new TicTacToeProblem(3);
        System.out.println(problem.move(0,0,1));
        System.out.println(problem.move(0,2,2));
        System.out.println(problem.move(2,2,1));
        System.out.println(problem.move(1,1,2));
        System.out.println(problem.move(2,0,1));
        System.out.println(problem.move(1,0,2));
        System.out.println(problem.move(2,1,1));
    }

}

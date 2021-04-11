package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 27-06-2020.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:

 Each row must contain the digits 1-9 without repetition.
 Each column must contain the digits 1-9 without repetition.
 Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

 A partially filled sudoku which is valid.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

 Example 1:

 Input:
 [
 ['5','3','.','.','7','.','.','.','.'],
 ['6','.','.','1','9','5','.','.','.'],
 ['.','9','8','.','.','.','.','6','.'],
 ['8','.','.','.','6','.','.','.','3'],
 ['4','.','.','8','.','3','.','.','1'],
 ['7','.','.','.','2','.','.','.','6'],
 ['.','6','.','.','.','.','2','8','.'],
 ['.','.','.','4','1','9','.','.','5'],
 ['.','.','.','.','8','.','.','7','9']
 ]
 Output: true
 Example 2:

 Input:
 [
 ['8','3','.','.','7','.','.','.','.'],
 ['6','.','.','1','9','5','.','.','.'],
 ['.','9','8','.','.','.','.','6','.'],
 ['8','.','.','.','6','.','.','.','3'],
 ['4','.','.','8','.','3','.','.','1'],
 ['7','.','.','.','2','.','.','.','6'],Pa
 ['.','6','.','.','.','.','2','8','.'],
 ['.','.','.','4','1','9','.','.','5'],
 ['.','.','.','.','8','.','.','7','9']
 ]
 Output: false
 Explanation: Same as Example 1, except with the 5 in the top left corner being
 modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */
public class SudokuValidationProblem {

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
        System.out.println(isSudokuValid(board));
        System.out.println(isSudokuValidSmart(board));
    }

    public static boolean isSudokuValid(char[][] board){
        if(board==null || board.length<9 || board[0].length<9)
            return false;
        boolean[][] row = new boolean[9][9];
        boolean[][] column = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int value = board[i][j] - '1';
                    int boxId = 3 * (i / 3) + (j / 3);
                    if (row[i][value] || column[j][value] || box[boxId][value]) {
                        return false;
                    }
                    row[i][value] = true;
                    column[j][value] = true;
                    box[boxId][value] = true;
                }
            }
        }
        return true;
    }

    //Smart Way of doing this
    public static boolean isSudokuValidSmart(char[][] board){
        if(board==null || board.length<9 || board[0].length<9)
            return false;
        Set<String> seen = new HashSet<>();
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    char value = board[i][j];
                    if (!seen.add(value + " found in row " + i) ||
                            !seen.add((value + " found in column " + j)) ||
                            !seen.add(value + " found in box " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}

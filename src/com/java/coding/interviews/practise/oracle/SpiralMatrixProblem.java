package com.java.coding.interviews.practise.oracle;

/**
 * Given a 2D array, print it in spiral form.
 *
 * Examples:
 *
 * Input:  {{1,    2,   3,   4},
 *               {5,    6,   7,   8},
 *              {9,   10,  11,  12},
 *             {13,  14,  15,  16 }}
 * Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 * Explanation: The output is matrix in spiral format.
 */

public class SpiralMatrixProblem {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printMatrixInSpiral(matrix);
    }

    private static void printMatrixInSpiral(int[][] matrix){
        if(matrix==null)
            return;
        int rowIndex=0;
        int lastRowIndex=matrix.length-1;
        int columnIndex=0;
        int lastColumnIndex=matrix[0].length-1;
        StringBuilder spiralMatrix = new StringBuilder();
        if(lastColumnIndex==0){
            for(int i=0;i<=lastRowIndex;i++){
                spiralMatrix.append(matrix[i][0]).append("-");
            }
        }else{
            while(rowIndex<=lastRowIndex && columnIndex<=lastColumnIndex){
                for(int i=columnIndex;i<=lastColumnIndex;i++){
                    spiralMatrix.append(matrix[rowIndex][i]).append("-");
                }
                rowIndex++;

                for(int i=rowIndex;i<=lastRowIndex;i++){
                    spiralMatrix.append(matrix[i][lastColumnIndex]).append("-");
                }
                lastColumnIndex--;
                if(rowIndex<=lastRowIndex){
                    for(int i=lastColumnIndex;i>=columnIndex;i--){
                        spiralMatrix.append(matrix[lastRowIndex][i]).append("-");
                    }
                    lastRowIndex--;
                }
                if(columnIndex<=lastColumnIndex){
                    for(int i=lastRowIndex;i>=rowIndex;i--){
                        spiralMatrix.append(matrix[i][columnIndex]).append("-");
                    }
                    columnIndex++;
                }

            }
        }
        System.out.println(spiralMatrix.toString());

    }
}

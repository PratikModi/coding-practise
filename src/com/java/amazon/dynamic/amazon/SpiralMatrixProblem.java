package com.java.amazon.dynamic.amazon;

/**
 * Print Matrix in Spiral Form
 */



public class SpiralMatrixProblem {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8}/*,
                {9,10,11,12},
                {13,14,15,16}*/
        };
        spiralMatrix(matrix);
        int[][] matrix_2 = {
                {133, 241, 22, 258, 187, 150, 79, 207, 196, 401, 366, 335, 198},
                {401, 55, 260, 363, 14, 318, 178, 296, 333, 296, 45, 37, 10},
                {112, 374, 79, 12, 97, 39, 310, 223, 139, 91, 171, 95, 126}
        };
        spiralMatrix(matrix_2);
    }

    public static void spiralMatrix(int[][] matrix){
        int lastRow = matrix.length-1;
        int lastColumn = matrix[0].length-1;
        int rowIndex=0;
        int columnIndex=0;
        StringBuilder spiralMatrix=new StringBuilder("");
        if(lastColumn==0){
            for(int i=0;i<=lastRow;i++){
                spiralMatrix.append(matrix[i][0]).append("-");
            }
        }else {
            while (rowIndex <= lastRow && columnIndex <= lastColumn) {
                for (int i = columnIndex; i <= lastColumn; i++) {
                    spiralMatrix.append(matrix[rowIndex][i]).append("-");
                }
                rowIndex++;

                for (int i = rowIndex; i <= lastRow; i++) {
                    spiralMatrix.append(matrix[i][lastColumn]).append("-");
                }
                lastColumn--;
                if(rowIndex<=lastRow) {
                    for (int i = lastColumn; i >= columnIndex; i--) {
                        spiralMatrix.append(matrix[lastRow][i]).append("-");
                    }
                    lastRow--;
                }
                if(columnIndex<=lastColumn) {
                    for (int i = lastRow; i >= rowIndex; i--) {
                        spiralMatrix.append(matrix[i][columnIndex]).append("-");
                    }
                    columnIndex++;
                }
            }
        }
        System.out.println(spiralMatrix.toString());
    }

}

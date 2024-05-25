package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 */
public class SetMatrixZeroProblem {
    public static void main(String[] args) {
        int[][] matrix= {{1,1,1},{1,0,1},{1,1,1}};
        Arrays.stream(matrix).forEach(e-> System.out.println(Arrays.toString(e)));
        System.out.println("==============================================");
        setMatrixToZero2(matrix);
        Arrays.stream(matrix).forEach(e-> System.out.println(Arrays.toString(e)));
    }


    public static void setMatrixToZero2(int[][] matrix){
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] isOriginalZero = new boolean[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]==0){
                    isOriginalZero[i][j]=true;
                }
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int currentRow = i;
                int currentCol = j;
                if(matrix[currentRow][currentCol]==0 && isOriginalZero[currentRow][currentCol]){
                    for(int c=0;c<col;c++){
                        matrix[currentRow][c]=0;
                    }
                    for(int r=0;r<row;r++){
                        matrix[r][currentCol]=0;
                    }
                }
            }
        }
    }

    public static void setMatrixToZero(int[][] matrix){
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return;
        boolean firstRow=false;
        boolean firstCol=false;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                    if(i==0)
                        firstRow=true;
                    if(j==0)
                        firstCol=true;
                }
            }
        }
        //Arrays.stream(matrix).forEach(e-> System.out.println(Arrays.toString(e)));
        System.out.println("==============================================");
        for(int i=1;i<matrix.length;i++){
            for (int j=1;j<matrix[i].length;j++){
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if(firstRow){
            for(int i=0;i<matrix[0].length;i++){
                matrix[0][i]=0;
            }
        }
        if(firstCol){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0]=0;
            }
        }
    }
}

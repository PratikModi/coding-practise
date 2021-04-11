package com.java.amazon.dynamic.amazon;

/**
 * Created by Pratik1 on 15-06-2020.
 */

import java.util.Arrays;

/**
 * Given a 2-D matrix representing an image, a location of a pixel in the screen and a color C,
 * replace the color of the given pixel and all adjacent same colored pixels with C.
 * For example, given the following matrix, and location pixel of (2, 2), and 'G' for green:

         B B W
         W W W
         W W W
         B B B

        Becomes

         B B G
         G G G
         G G G
         B B B
*/
public class MatrixColorChangeProblem {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'B','B','W'},{'W','W','W'},{'W','W','W'},{'B','B','B'}};
        for(int i=0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("==========BECOMES===============");
        changeMatrixColor(matrix,new int[]{2,2},'G');
        for(int i=0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void changeMatrixColor(char[][] matrix, int[] index, char c){
        if(matrix==null || matrix.length==0 || index==null || index.length<2)
            return;
        int X=index[0];
        int Y=index[1];
        char color = matrix[X][Y];
        dfs(matrix,X,Y,color,c);
    }

    private static void dfs(char[][] matrix,int i,int j, char sc, char dc){
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[i].length || matrix[i][j]!=sc)
            return;
        matrix[i][j]=dc;
        dfs(matrix,i+1,j,sc,dc);
        dfs(matrix,i-1,j,sc,dc);
        dfs(matrix,i,j+1,sc,dc);
        dfs(matrix,i,j-1,sc,dc);
        return;
    }

}

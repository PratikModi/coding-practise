package com.java.amazon.dynamic.amazon;

/**
 * Created by Pratik1 on 22-06-2020.
 */

import java.util.Arrays;

/**
 * You are given an N by M matrix of 0s and 1s. Starting from the top left corner,
 * how many ways are there to reach the bottom right corner?
 * You can only move right and down. 0 represents an empty space
 * while 1 represents a wall you cannot walk through.
 * For example, given the following matrix:
 [[0, 0, 1],
 [0, 0, 1],
 [1, 0, 0]]
 Return two, as there are only two ways to get to the bottom right:

 Right, down, down, right
 Down, right, down, right
 The top left corner and bottom right corner will always be 0.
 */
public class MatrixTotalWaysProblem {
    public static void main(String[] args) {
        int[][] matrix = {{0,0,1},{0,0,1},{1,0,0}};
        int result = findWays(matrix);
        System.out.println(result);
        result = findWaysDP(matrix);
        System.out.println(result);
    }

    public static int findWays(int[][] matrix){
        if(matrix==null || matrix.length==0)
            return 0;
        int result=0;
        result+=helper(matrix,0,0);
        return result;
    }

    private static int helper(int[][] matrix,int row,int column){
        if(row==matrix.length-1 && column==matrix.length-1)
            return 1;
        int right=0,down=0;
        if(column<matrix.length-1 && matrix[row][column+1]!=1){
            right = helper(matrix,row,column+1);
        }
        if(row<matrix.length-1 && matrix[row+1][column]!=1){
            down = helper(matrix,row+1,column);
        }
        return right+down;
    }

    public static int findWaysDP(int[][] matrix){
        if(matrix==null || matrix.length==0)
            return 0;
        int[][] result = matrix;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(result[i][j]==1)
                    result[i][j]=0;
                else if(i==0 && j==0)
                    result[i][j]=1;
                else if(i==0)
                    result[i][j]=result[i][j-1];
                else if(j==0)
                    result[i][j]=result[i-1][j];
                else
                    result[i][j]=result[i-1][j]+result[i][j-1];
            }
        }
        Arrays.asList(result).stream().forEach(e-> System.out.println(Arrays.toString(e)));
        //System.out.println(Arrays.toString(result));
        return result[matrix.length-1][matrix.length-1];
    }

}

package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * Created by Pratik1 on 31-05-2020.
 */
/*
    Given an N by M matrix consisting only of 1's and 0's,
    find the largest rectangle containing only 1's and return its area.
    For example, given the following matrix:
 [
     [1, 0, 0, 0],
     [1, 0, 1, 1],
     [1, 0, 1, 1],
     [0, 1, 0, 0]
 ]

Return 4.
 */
public class MatrixSquareProblem {

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(findMaxSquare(matrix));
    }

    public static int findMaxSquare(int[][] matrix){
        int result=0;
        if(matrix==null && matrix.length==0){
            return result;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(i==0 || j==0){
                    dp[i][j]=matrix[i][j];
                }else{
                    if(matrix[i][j]==1) {
                        dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                    }
                }
                result=Math.max(result,dp[i][j]);
            }
        }
        for(int i=0;i<matrix.length;i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("=========================");
        for(int i=0;i<matrix.length;i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return result;
    }


}

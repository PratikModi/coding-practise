package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 22-06-2020.
 */

/**
 * Count all possible paths from top left to bottom right of a mXn matrix
 The problem is to count all the possible paths from top left to bottom right of a mXn
 matrix with the constraints that from each cell you can either move only to right or down

 Examples :

 Input :  m = 2, n = 2;
 Output : 2
 There are two paths
 (0, 0) -> (0, 1) -> (1, 1)
 (0, 0) -> (1, 0) -> (1, 1)

 Input :  m = 2, n = 3;
 Output : 3
 There are three paths
 (0, 0) -> (0, 1) -> (0, 2) -> (1, 2)
 (0, 0) -> (0, 1) -> (1, 1) -> (1, 2)
 (0, 0) -> (1, 0) -> (1, 1) -> (1, 2)
 */
public class MatrixCountAllPathProblem {

    public static void main(String[] args) {
        System.out.println(countWays(5,5));
    }

    public static int countWays(int rows, int cols){
        int[][] dp = new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(i==0 || j==0)
                    dp[i][j]=1;
                else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[rows-1][cols-1];
    }

}

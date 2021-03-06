package com.java.coding.interviews.practise.amazon;

/**
 * Created by Pratik1 on 22-06-2020.
 */

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 Example:
 Input:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 Output: 7
 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MatrixTraverseMinimumPathSumProblem {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minimumPathSum(matrix));
        matrix = new int[][]{{1,2,5},{3,2,1}};
        System.out.println(minimumPathSum(matrix));
    }

    public static int minimumPathSum(int[][] matrix){
        if(matrix==null || matrix.length==0){
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                dp[i][j]+=matrix[i][j];
                if(i>0 && j>0){
                    dp[i][j]+=Math.min(dp[i-1][j],dp[i][j-1]);
                }else if(i>0){
                    dp[i][j]+=dp[i-1][j];
                }else if(j>0){
                    dp[i][j]+=dp[i][j-1];
                }
            }
        }
        return dp[matrix.length-1][matrix[0].length-1];
    }
}

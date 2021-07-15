package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given a m * n matrix, find the max sum for path in the matrix. From row 'a' the next value can only be chosen from row 'a' + 1
 * and a penalty is assigned to the difference in column position. So, to jump from column 1 to column 3,
 * the penalty would be 2 and should be subtracted from the sum calculation.
 *
 * input =
 * [1,5,6,7
 * 2,4,5,6
 * 7,9,10,11]
 */
public class MatrixMaxPathSumProblem {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,5,6,7},
                {2,4,5,6},
                {7,9,10,11}
        };
        System.out.println(maxPath(matrix));
    }

    public static int maxPath(int [][] matrix){
        int [][] dp = new int[matrix.length][];
        int maxPath = 0;
        for(int i=0;i<matrix.length;i++){
            dp[i] = new int[matrix[0].length];
        }
        for(int i=0;i<matrix[0].length;i++){
            dp[0][i] = matrix[0][i];
        }
        Arrays.stream(dp).forEach(e-> System.out.println(Arrays.toString(e)));
        for(int i=0;i<matrix.length-1;i++){
            int maxLeft = 0;
            int maxRight = 0;
            for(int j=0;j<matrix[0].length;j++){
                System.out.println("Max Left==>"+(maxLeft-1)+"-->"+dp[i][j]);
                maxLeft = Math.max(dp[i][j], maxLeft-1);
                dp[i+1][j] = maxLeft+matrix[i+1][j];
                System.out.println("=============================================");
                Arrays.stream(dp).forEach(e-> System.out.println(Arrays.toString(e)));
            }
            System.out.println("=============================================");
            Arrays.stream(dp).forEach(e-> System.out.println(Arrays.toString(e)));
            for(int j=matrix[0].length-1;j>=0;j--){
                maxRight = Math.max(dp[i][j], maxRight-1);
                System.out.println("Max Right==>"+maxRight);
                dp[i+1][j] = Math.max(dp[i+1][j], maxRight+matrix[i+1][j]);
            }
            System.out.println("=============================================");
            Arrays.stream(dp).forEach(e-> System.out.println(Arrays.toString(e)));
        }
        for(int i=0;i<dp[0].length;i++){
            maxPath = Math.max(maxPath, dp[dp.length-1][i]);
        }
        return maxPath;
    }
}

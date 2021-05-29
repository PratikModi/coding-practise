package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 30-05-2020.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given 2 integer array A and B of length i and j.
 * Connect 2 points if A[i]=B[j]. No Lines should be crossed.
 *     1  2  3  4
 *     |  |  |  | ==> 4
 *     1  2  3  4
 */
public class UncrossedLinesProblem {

    public static void main(String[] args) {
        int[] A = {1,2,3,4};
        int[] B = {1,2,3,4};
        int[] C = {4,3,2,1};
        int[] D = {4,3};
        System.out.println(findUncrossedLines(A,B));
        System.out.println(findUncrossedLines(A,C));
        System.out.println(findUncrossedLines(A,D));
    }

    public static int findUncrossedLines(int[] A, int[] B){

        if(A==null||B==null||A.length==0||B.length==0)
            return 0;
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }
                else if(A[i-1]==B[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

}

package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given an integer n, find the least number of perfect square numbers (like 1, 4, 9, 16, ...) that sum up to n.
 *
 * Example
 * Input: n = 12
 * Output: 3  (because 12 = 4 + 4 + 4)
 * Input: n = 13
 * Output: 2  (because 13 = 4 + 9)
 */
//Time Complexity: O(n*log n);
//Space Complexity: O(n);
public class PerfectSquareProblem {

    public static void main(String[] args) {
        System.out.println(perfectSquare(12));
        System.out.println(perfectSquare(13));
    }

    private static int perfectSquare(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            for(int s=1;s*s<=i;s++){
                dp[i]=Math.min(dp[i],dp[i-s*s]+1);
            }
        }
        return dp[n];
    }

}

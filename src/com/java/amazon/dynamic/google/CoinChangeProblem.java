package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 23-06-2020.
 */

/**
 * Given a value N, if we want to make change for N cents, and we have infinite supply
 * of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change?
 * The order of coins does not matter.
 * For example, for N = 4 and S = {1,2,3},
 * are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 * So output should be 4. For N = 10 and S = {2, 5, 3, 6},
 * there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
 * So the output should be 5.
 */
public class CoinChangeProblem {
    public static void main(String[] args) {
        System.out.println(findWays(new int[]{1,2,3},4));
    }

    public static int findWays(int[] coins,int value){
        if(coins==null || coins.length==0)
            return -1;
        int[] dp = new int[value+1];
        //if value is "0" -->
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=value;j++){
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[value];
    }
}

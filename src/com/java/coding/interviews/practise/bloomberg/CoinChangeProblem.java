package com.java.coding.interviews.practise.bloomberg;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChangeProblem {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5},11));
        System.out.println(coinChange(new int[]{2},3));
        System.out.println(coinChange(new int[]{1},0));
    }


    public static int coinChange(int[] coins, int value){
        if(coins==null || coins.length==0){
            return -1;
        }
        int[] dp = new int[value+1]; //starts from 0 to value
        dp[0]=0;
        for(int i=1;i<=value;i++){
            dp[i]=value+1;
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
        }
        return dp[value]>value?-1:dp[value];
    }

}

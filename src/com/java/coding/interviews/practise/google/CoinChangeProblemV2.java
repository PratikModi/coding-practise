package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 23-06-2020.
 */

import java.util.Arrays;

/**
 * Given a value V, if we want to make change for V cents, and we have infinite supply of each of
 * C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?
 Examples:

 Input: coins[] = {25, 10, 5}, V = 30
 Output: Minimum 2 coins required
 We can use one coin of 25 cents and one of 5 cents

 Input: coins[] = {9, 6, 5, 1}, V = 11
 Output: Minimum 2 coins required
 We can use one coin of 6 cents and 1 coin of 5 cents
 */
public class CoinChangeProblemV2 {
    public static void main(String[] args) {
        int[] coins = {25,10,5};
        System.out.println(findWays(coins,30));
        coins = new int[] {9,6,5,1};
        System.out.println(findWays(coins,11));
        coins = new int[] {2};
        System.out.println(findWays(coins,3));
    }

    public static int findWays(int[] coins, int value){
        if(coins==null || coins.length==0)
            return -1;
        int[] dp = new int[value+1];
        //if value is 0
        dp[0]=0;
        for(int i=1;i<=value;i++){
            dp[i]=value+1;
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[value]>value?-1:dp[value];
    }
}

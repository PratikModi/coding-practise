package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class MaximumProfitProblem {

    public static void main(String[] args) {
        System.out.println(calculateMaxProfit(new int[]{10, 22, 5, 75, 65, 80},2));
    }

    public static int calculateMaxProfit(int[] prices, int k){
        int days = prices.length;
        int[][] dp = new int[k+1][days+1];
        for(int i=0;i<=k;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<=days;i++){
            dp[0][i]=0;
        }

        for(int i=1;i<=k;i++){
            for(int j=1;j<days;j++){
                int max_so_far=0;
                for(int m=0;m<j;m++){
                    max_so_far = Math.max(prices[j]-prices[m]+dp[i-1][m],max_so_far);
                    dp[i][j] = Math.max(max_so_far,dp[i][j-1]);
                }
            }
        }
        return dp[k][days-1];
    }

}

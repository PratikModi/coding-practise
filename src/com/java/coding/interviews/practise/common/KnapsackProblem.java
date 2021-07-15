package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 12-04-2020.
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        System.out.println(filledKnapsackProblem(new int[]{2,3,4,5},new int[] {1,2,5,6},8,4));
    }

    public static int filledKnapsackProblem(int[] weight, int[] profit, int maxWeight, int n){
        int[][] dp = new int[n+1][maxWeight+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=maxWeight;j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }
                else if(j>=weight[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j],profit[i-1]+dp[i-1][j-weight[i-1]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for(int k=0;k<dp.length;k++){
            System.out.println(Arrays.toString(dp[k]));
        }
        return dp[n][maxWeight];
    }

}

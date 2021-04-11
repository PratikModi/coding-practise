package com.java.amazon.dynamic;

/**
 * Created by Pratik1 on 15-02-2020.
 */
public class StairProblem {

    public static int findWays(int noOfSteps, int[] ways){

        int[] dp = new int[noOfSteps+1];
        dp[0]=1;
        for(int i=1;i<=noOfSteps;i++){
            for(int w:ways){
                if(i>=w){
                    dp[i]+=dp[i-w];
                }
            }
        }
        return dp[noOfSteps];
    }

    public static void main(String[] args) {
        System.out.println(findWays(4,new int[]{1,2}));
    }

}

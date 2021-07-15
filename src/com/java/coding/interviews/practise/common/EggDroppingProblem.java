package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 08-04-2020.
 */
public class  EggDroppingProblem {

    public static void main(String[] args) {
        System.out.println(findAttempts(3,5));
    }

    public static int findAttempts(int eggs, int floors){
        int temp = 0;
        int[][] dp = new int[eggs+1][floors+1];
        for(int i=0;i<=floors;i++){
            dp[1][i]=i;
        }
        for(int j=0;j<=eggs;j++){
            dp[j][1]=1;
        }

        for(int e=2;e<=eggs;e++){
            for(int f=2;f<=floors;f++){
                dp[e][f]=Integer.MAX_VALUE;
                for(int k=2;k<=f;k++){
                    temp=1+Math.max(dp[e-1][f-1],dp[e][f-k]);
                }
                dp[e][f] =Math.min(dp[e][f],temp);
            }
        }
        return dp[eggs][floors];
    }

}

package com.java.amazon.dynamic.facebook;

/**
 * Created by Pratik1 on 21-06-2020.
 */

/**
 * Given a positive integer n, find the smallest number of squared integers which sum to n.
 * For example, given n = 13, return 2 since 13 = 3^2 + 2^2 = 9 + 4.
 * Given n = 27, return 3 since 27 = 3^2 + 3^2 + 3^2 = 9 + 9 + 9.
 */
public class MinimumSquareNumberProblem {
    public static void main(String[] args) {
        System.out.println(getMinimumSquareNumber(10));
        System.out.println(getMinimumSquareNumberDP(1));
    }

    public static int getMinimumSquareNumber(int N){
        int result=0;
        if(N<=3)
            return N;
        result=N;
        for(int i=1;i<N;i++){
            int temp = i*i;
            if(temp>N)
                break;
            result = Math.min(result,1+getMinimumSquareNumber(N-temp));
        }
        return result;
    }

    public static int getMinimumSquareNumberDP(int N){
        if(N<4)
            return N;
        int[] dp = new int[N+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
        for(int i=4;i<=N;i++){
            dp[i]=i;
            for(int x=1;x<=Math.ceil(Math.sqrt(i));x++) {
                int temp = x * x;
                if (temp > i)
                    break;
                dp[i] = Math.min(dp[i], 1 + dp[i-temp]);
            }
        }

        return dp[N];
    }
}

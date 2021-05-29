package com.java.coding.interviews.practise.facebook;

/**
 * Created by Pratik1 on 25-05-2020.
 */
/*
    Given an array of numbers representing the stock prices of a company in chronological order
    and an integer k, return the maximum profit you can make from k buys and sells.
    You must buy the stock before you can sell it, and you must sell the stock before
    you can buy it again.
    For example, given k = 2 and the array [5, 2, 4, 0, 1], you should return 3.
 */
public class MaxProfitStockBuySaleProblem {

    public static void main(String[] args) {
        int[] P = new int[]{5,2,4,0,1};
        int K=2;
        System.out.println(calculateMaxProfit(P,K));
        System.out.println(calculateMaxProfitOptimized(P,K));
    }
    //O(Kn^2)
    public static int calculateMaxProfit(int[] P, int K){
        if(P==null ||P.length==0)
            return 0;
        int N = P.length;
        int DP[][] = new int[K+1][N+1];
        //If No Transaction profit will be 0.Candy
        for(int i=0;i<=K;i++){
            DP[i][0]=0;
        }
        //On 0th Day you can't make any profit
        for(int j=0;j<=N;j++){
            DP[0][j]=0;
        }
        for(int i=1;i<=K;i++){
            for(int j=1;j<N;j++){
                int max_so_far = 0;
                for(int m=0;m<j;m++){
                    max_so_far = Math.max(max_so_far,P[j]-P[m]+DP[i-1][m]);
                }
                DP[i][j] = Math.max(max_so_far,DP[i][j-1]);
            }
        }
        return DP[K][N-1];
    }
    //O(KN)

    /**
     * MAX(P[j]-P[m]+DP[i-1][m])
     * P[j]+MAX(DP[i-1][m]-P[m])
     * for j in all range [0...j-1]
     * P[j]+MAX(prevDiff,DP[i-1][m-1]-P[m-1])
     * prefDiff = MAX(DP[i-1][m]-P[m])
     *
     */
    public static int calculateMaxProfitOptimized(int[] P, int K){
        if(P==null ||P.length==0)
            return 0;
        int N = P.length;
        int DP[][] = new int[K+1][N+1];
        //If No Transaction profit will be 0.
        for(int i=0;i<=K;i++){
            DP[i][0]=0;
        }
        //On 0th Day you can't make any profit
        for(int j=0;j<=N;j++){
            DP[0][j]=0;
        }
        for(int i=1;i<=K;i++){
            int max_so_far = Integer.MIN_VALUE;
            for(int j=1;j<N;j++){
                max_so_far = Math.max(max_so_far,DP[i-1][j-1]-P[j-1]);
                DP[i][j] = Math.max(max_so_far+P[j],DP[i][j-1]);
            }
        }
        return DP[K][N-1];
    }


}

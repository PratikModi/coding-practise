package com.java.amazon.dynamic.google;

/**
 * The painter’s partition problem
 *
 * We have to paint n boards of length {A1, A2…An}. There are k painters available and
 * each takes 1 unit time to paint 1 unit of board. The problem is to find the minimum time to get
 * this job done under the constraints that any painter will only paint continuous sections of boards,
 * say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 *
 * Examples:
 *
 * Input : k = 2, A = {10, 10, 10, 10}
 * Output : 20.
 * Here we can divide the boards into 2
 * equal sized partitions, so each painter
 * gets 20 units of board and the total
 * time taken is 20.
 *
 * Input : k = 2, A = {10, 20, 30, 40}
 * Output : 60.
 * Here we can divide first 3 boards for
 * one painter and the last board for
 * second painter.
 */

public class PainterPartitionProblem {

    public static void main(String[] args) {
        int result = findMinimumPaintingUnits(new int[]{10,20,30,40},1);
        System.out.println(result);
    }

    public static int findMinimumPaintingUnits(int[] A, int K){
        if(A==null || A.length==0 || K==0)
            return 0;
        int N = A.length;
        int[] sum = new int[N+1];
        for(int i=1;i<=N;i++){
            sum[i]=sum[i-1]+A[i-1];
        }
        int[][] dp = new int[K+1][N+1];

        //If there is only 1 Painter
        for(int i=1;i<=N;i++){
            dp[1][i]=sum[i];
        }
        //if there is only 1 Paining
        for(int i=1;i<=K;i++){
            dp[i][1]=A[0];
        }

        for(int i=2;i<=K;i++){
            for(int j=2;j<=N;j++){
                int BEST = Integer.MAX_VALUE;
                for(int p=1;p<=j;p++){
                    BEST=Math.min(BEST,Math.max(dp[i-1][p],sum[j]-sum[p]));
                    dp[i][j]=BEST;
                }
            }
        }
        return dp[K][N];
    }

}

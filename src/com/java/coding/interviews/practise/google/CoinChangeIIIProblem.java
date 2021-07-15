package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given denominations D and a max value MAX, find the smallest set of coins that can exactly construct any value 1 <= n <= MAX.
 */
public class CoinChangeIIIProblem {
    public static void main(String[] args) {
        int[] coins = {1,5,10,15};
        int W = 25;
        int[][] result = findMinimumCoins(coins,W);
        Arrays.stream(result).forEach(e-> System.out.println(Arrays.toString(e)));
    }

    public static int[][] findMinimumCoins(int[] coins, int W){
        int N = coins.length;
        int[][] result = new int[W+1][N];
        int[][] DP = new int[N][W+1];
        for(int i=0;i<N;i++){
            for(int w=coins[i];w<=W;w++){
                if(i==0){
//                    if(w%coins[i]==0) {
                        result[w][i] += w / coins[i];
                        DP[i][w] = w / coins[i];
  //                  }
                }else{
                    DP[i][w]=Math.min(DP[i-1][w],DP[i][w-coins[i]]+1);
                    if(DP[i][w]==DP[i][w-coins[i]]+1){
                        //System.out.println(w);
                        result[w]= Arrays.copyOf(result[w-coins[i]],N);
                        result[w][i]+=1;
                    }
                }
            }
        }
        return result;
    }

}

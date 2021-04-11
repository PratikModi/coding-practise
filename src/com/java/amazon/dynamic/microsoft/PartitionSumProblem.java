package com.java.amazon.dynamic.microsoft;

/**
 * Given an array of positive integers, divide the array into two subsets such that the difference
 * between the sum of the subsets is as small as possible.
 *
 * For example, given [5, 10, 15, 20, 25], return the sets {10, 25} and {5, 15, 20},
 * which has a difference of 5, which is the smallest possible difference.
 */

public class PartitionSumProblem {
    public static void main(String[] args) {
        int[] A = new int[] {5,10,15,20,25};
        System.out.println(minimumDifferent(A));
    }

    public static int minimumDifferent(int[] A){
        if(A==null || A.length==0)
            return -1;
        int N = A.length;
        int sum=0;
        for(int i=0;i<N;i++){
            sum+=A[i];
        }
        boolean[][] dp = new boolean[N+1][sum+1];
        for(int i=0;i<=N;i++){
            dp[i][0]=true;
        }
        for(int i=0;i<=sum;i++){
            dp[0][i]=false;
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=sum;j++){
                dp[i][j]=dp[i-1][j]; //Excluding element
                if(A[i-1]<=j){
                    dp[i][j]=dp[i][j] | dp[i-1][j-A[i-1]]; //Including Element
                }
            }
        }
        int diff = Integer.MAX_VALUE;
        for(int j=sum/2;j>=0;j--){
            if(dp[N][j]) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }
}

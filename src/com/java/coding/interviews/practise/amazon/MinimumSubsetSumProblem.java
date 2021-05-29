package com.java.coding.interviews.practise.amazon;

/**
 * Partition a set into two subsets such that the difference of subset sums is minimum
 * Last Updated: 26-10-2020
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
 * If there is a set S with n elements, then if we assume Subset1 has m elements,
 * Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 * Example:
 *
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 */
public class MinimumSubsetSumProblem {

    public static void main(String[] args) {
        int[] A = {1,6,11,5};
        System.out.println(findMinimumSubsetDifference(A));
    }

    private static int findMinimumSubsetDifference(int[] A){
        if(A==null || A.length==0)
            return -1;
        int sum=0;
        for(int i : A){
            sum+=i;
        }
        int N=A.length;
        boolean[][] DP = new boolean[N+1][sum+1];
        for(int i=0;i<=N;i++){
            DP[i][0]=true;
        }
        for(int i=0;i<=sum;i++){
            DP[0][i]=true;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=sum;j++){
                DP[i][j]=DP[i-1][j]; // Not taking element
                if(A[i-1]>=j)
                    DP[i][j]=DP[i][j] || DP[i][j-A[i-1]]; //Taking element
            }
        }
        int diff=Integer.MAX_VALUE;
        for(int i=0;i<=sum/2;i++){
            if(DP[N][i]==true)
                diff=sum-2*i;
        }
        return diff;
    }
}

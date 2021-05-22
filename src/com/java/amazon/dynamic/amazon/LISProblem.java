package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

public class LISProblem {
    public static void main(String[] args) {
        int[] A = {10,9,2,5,3,4};
        System.out.println(findLCS(A));
        System.out.println(findLIS(A));
    }
    //O(n^2)
    public static int findLIS(int[] A){
        int max=0;
        int N = A.length;
        int[] DP = new int[N];
        Arrays.fill(DP,1);

        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(A[i]>A[j] && DP[i]<DP[j]+1){
                    DP[i]=DP[j]+1;
                    max=Math.max(max,DP[i]);
                }
            }
        }
        return max;
    }

    //O(nlogn)
    public static int findLCS(int[] A){
        if(A==null ||A.length==0)
            return 0;
        int N=A.length;
        int lisIndex=1;
        int[] dp = new int[N];
        dp[0]=A[0];
        for(int i=1;i<N;i++){
            if(A[i]<dp[0]){
                dp[0]=A[i];
            }else if(A[i]>dp[lisIndex-1]){
                dp[lisIndex++]=A[i];
            }else{
                dp[findIndex(dp,-1,lisIndex-1,A[i])] =A[i];
            }
        }
        System.out.println(Arrays.toString(dp));
        return lisIndex;
    }

    public static int findIndex(int[] A, int low, int high, int key){
        while((high-low)>1){
            int mid=low+(high-low)/2;
            if(A[mid]>=key){
                high=mid;
            }else{
                low=mid;
            }
        }
        return high;
    }

}

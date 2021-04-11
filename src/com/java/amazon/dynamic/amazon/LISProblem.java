package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

public class LISProblem {
    public static void main(String[] args) {
        int[] A = {47, 76, 6, 99, 97, 48, 18, 97, 10, 15, 92, 101, 7, 42, 35, 15, 14, 18, 47, 95, 7, 87, 55, 2, 59, 91, 14, 38, 91, 20, 16, 88, 74, 50, 63, 24, 93, 97, 10, 75, 85, 87, 1, 1, 21, 77, 22, 88, 65, 89, 81, 6, 63, 8, 89, 45, 66, 57, 39, 42, 87, 31, 91, 4, 94, 80, 54, 80, 32, 10, 44, 21, 24, 69, 73, 50, 36, 15, 89, 11, 61};
        System.out.println(findLCS(A));
    }

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

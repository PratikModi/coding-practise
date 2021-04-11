package com.java.amazon.dynamic;

import java.util.Arrays;

/**
 * Created by Pratik1 on 15-02-2020.
 */
public class LongestBitonicSequence {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(findLis(new int[]{1,2,3,5,4})));
        int[] A = new int[] {1, 11, 2, 10, 4, 5, 2, 1};
        int[] A_REV = new int[] {1, 2, 5, 4, 10, 2, 11, 1};
        int[] a = findLis(A);
        int[] b = findLis(A_REV);
        System.out.println(Arrays.toString(findLis(A)));
        System.out.println(Arrays.toString(findLis(A_REV)));
        int result =0;
        for(int i=0;i<a.length;i++){
            result = Math.max(Math.abs(a[i]+b[i]-1),result);
        }
        System.out.println(result);
    }

    public static int[] findLis(int[] A){
        int[] dp = new int[A.length];
        Arrays.fill(dp,1);
        for(int i=1;i<A.length;i++){
            for(int j=0; j<i;j++){
                if(A[i] > A[j] && dp[i]<dp[j]+1){
                    dp[i] = dp[j]+1;
                }
            }
        }
        return dp;
    }


}

package com.java.coding.interviews.practise.uipath;

import java.util.Arrays;

/**
 * Remove an element to maximize the GCD of the given array
 * Difficulty Level : Hard
 * Last Updated : 07 May, 2021
 * Given an array arr[] of length N ≥ 2. The task is to remove an element from the given array such that the GCD of the array after removing it is maximized.
 *
 * Examples:
 *
 * Input: arr[] = {12, 15, 18}
 * Output: 6
 * Remove 12: GCD(15, 18) = 3
 * Remove 15: GCD(12, 18) = 6
 * Remove 18: GCD(12, 15) = 3
 *
 * Input: arr[] = {14, 17, 28, 70}
 */
public class MaximumGCDProblem {

    public static void main(String[] args) {
        int[] A = {14, 17, 28, 70};
        int result = findMaximumGCD(A);
        System.out.println(result);
    }

    public static int findMaximumGCD(int[] A){
        if(A==null || A.length==0)
            return -1;
        int N = A.length;
        int[] prefix = new int[N];
        int[] suffix = new int[N];

        prefix[0]=A[0];
        for(int i=1;i<N;i++){
            prefix[i]=findGCD(prefix[i-1],A[i]);
        }

        suffix[N-1]=A[N-1];
        for(int i=N-2;i>=0;i--){
            suffix[i]=findGCD(suffix[i+1],A[i]);
        }
        System.out.println(Arrays.toString(prefix));
        System.out.println(Arrays.toString(suffix));
        int ans = Math.max(suffix[0],prefix[N-1]);
        for(int i=1;i<N-1;i++){
            System.out.println((i-1)+"--"+(i+1));
            ans=Math.max(ans,findGCD(prefix[i-1],suffix[i+1]));
        }
        return ans;
    }


    private static int findGCD(int A, int B){
        if(B==0)
            return A;
        return findGCD(B,A%B);
    }

}

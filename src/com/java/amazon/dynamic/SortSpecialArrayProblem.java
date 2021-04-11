package com.java.amazon.dynamic;

import java.util.Arrays;

/**
 * Sort an array of 0s, 1s and 2s
 * Last Updated: 28-05-2020
 * Given an array A[] consisting 0s, 1s and 2s.
 * The task is to write a function that sorts the given array.
 * The functions should put all 0s first, then all 1s and all 2s in last.
 *
 * Examples:
 *
 * Input: {0, 1, 2, 0, 1, 2}
 * Output: {0, 0, 1, 1, 2, 2}
 *
 * Input: {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}
 * Output: {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}
 */
public class SortSpecialArrayProblem {
    public static void main(String[] args) {
        int[] A = new int[] {0,1,2,0,1,2};
        sortArray(A);
        System.out.println(Arrays.toString(A));
    }
    public static void sortArray(int[] A){
        if(A==null || A.length<2)
            return;
        int L=0,H=A.length-1,M=0;
        while(M<=H){
            if(A[M]==0){
                int temp = A[L];
                A[L]=A[M];
                A[M]=temp;
                M++;
                L++;
            }else if(A[M]==1){
                M++;
            }else{
                int temp = A[H];
                A[H]=A[M];
                A[M]=temp;
                H--;
            }
        }
    }
}

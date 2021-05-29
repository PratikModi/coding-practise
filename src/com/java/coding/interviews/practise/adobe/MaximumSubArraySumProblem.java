package com.java.coding.interviews.practise.adobe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Largest Sum Contiguous Subarray
 * Difficulty Level : Medium
 * Last Updated : 13 May, 2021
 * Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers that has the largest sum.
 *
 * kadane-algorithm
 *
 *
 */

public class MaximumSubArraySumProblem {

    public static void main(String[] args) {
        int A[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(findMaxSubArraySum(A));
    }

    public static int findMaxSubArraySum(int[] A){
        int MAX = Integer.MIN_VALUE;
        int current_max=0;
        int start=0,end=0,s=0;
        int N = A.length;
        for(int i=0;i<N;i++){
            current_max+=A[i];
            if(MAX<current_max) {
                MAX=current_max;
                start = s;
                end = i;
            }
            if(current_max<0){
                current_max=0;
                s=i+1;
            }
        }
        System.out.println(start+"--"+end);
        return MAX;
    }
}

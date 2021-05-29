package com.java.coding.interviews.practise.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find subarray with given sum | Set 1 (Nonnegative Numbers)
 * Difficulty Level : Medium
 *  Last Updated : 10 Mar, 2021
 * Given an unsorted array of nonnegative integers, find a continuous subarray which adds to a given number.
 * Examples :
 *
 * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
 * Ouptut: Sum found between indexes 2 and 4
 * Sum of elements between indices
 * 2 and 4 is 20 + 3 + 10 = 33
 *
 * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
 * Ouptut: Sum found between indexes 1 and 4
 * Sum of elements between indices
 * 1 and 4 is 4 + 0 + 0 + 3 = 7
 *
 * Input: arr[] = {1, 4}, sum = 0
 * Output: No subarray found
 * There is no subarray with 0 sum
 * There may be more than one subarrays with sum as the given sum. The following solutions print first such subarray.
 */
public class SubArraySumProblem {

    public static void main(String[] args) {
        int[] A = {1, 4, 20, 3, 10, 5};
        int S = 33;
        System.out.println(findSubArrayWithSum(A,S));
    }

    public static List<Integer> findSubArrayWithSum(int[] A, int S){
        List<Integer> result = new ArrayList<>();
        if(A==null || A.length==0)
            return result;
        int start=0;
        int current_sum=A[0];
        int N = A.length;
        for(int i=1;i<N;i++){
            while(current_sum > S && start <(i-1)){
                current_sum-=A[start++];
            }
            if(current_sum==S){
                result.addAll(Arrays.asList(start,(i-1)));
            }
            if(i<N){
                current_sum+=A[i];
            }
        }
        return result;
    }

}

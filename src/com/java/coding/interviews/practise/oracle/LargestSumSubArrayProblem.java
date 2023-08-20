package com.java.coding.interviews.practise.oracle;

/**
 * Given an array arr[] of size N. The task is to find the sum of the contiguous subarray within a arr[] with the largest sum.
 * Largest Sum Contiguous Subarray (Kadane’s Algorithm)
 */
//Time Complexity: O(N)
//Auxiliary Space: O(1)
public class LargestSumSubArrayProblem {

    public static void main(String[] args) {
        int[] A = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println(findLargestSum(A));
    }

    public static int findLargestSum(int[] A){
        int max_so_far = Integer.MIN_VALUE;
        int max_ending = 0;
        for(int i=0;i<A.length;i++){
            max_ending = max_ending+A[i];
            if(max_so_far<max_ending){
                max_so_far = max_ending;
            }
            if(max_ending<0)
                max_ending=0;
        }
        return max_so_far;
    }
}

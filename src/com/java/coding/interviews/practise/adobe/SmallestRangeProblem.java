package com.java.coding.interviews.practise.adobe;

import java.util.Arrays;

/**
 * Given heights of n towers and a value k. We have to either increase or decrease height of every tower by k (only once) where k > 0.
 * The task is to minimize the difference between the heights of the longest and the shortest tower after modifications, and output this difference.
 *
 * Examples:
 *
 * Input : arr[] = {1, 15, 10}, k = 6
 * Output : Maximum difference is 5.
 * Explanation : We change 1 to 6, 15 to
 * 9 and 10 to 4. Maximum difference is 5
 * (between 4 and 9). We can't get a lower
 * difference.
 *
 * Given an array nums of integers, for each integer nums[i] we need to choose either x = -k or x = k, and add x to nums[i] (only once).
 *
 * After this process, we have some array result.
 *
 * Return the smallest possible difference between the maximum value of result and the minimum value of result.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1], k = 0
 * Output: 0
 * Explanation: result = [1]
 * Example 2:
 *
 * Input: nums = [0,10], k = 2
 * Output: 6
 * Explanation: result = [2,8]
 * Example 3:
 *
 * Input: nums = [1,3,6], k = 3
 * Output: 3
 * Explanation: result = [4,6,3]
 */
public class SmallestRangeProblem {
    public static void main(String[] args) {
        int A[] = {1, 15, 10}, k = 6;
        System.out.println(findSmallestRange(A,k));
    }

    public static int findSmallestRange(int[] A, int K){
        int N = A.length;
        Arrays.sort(A);
        int result = A[N-1] - A[0];
        for(int i=1;i<N;i++){
            int min1 = A[0]+K;
            int max1 = A[N-1]-K;
            int min2 = A[i]-K;
            int max2 = A[i-1]+K;
            result = Math.min(result, Math.max(max1,max2)-Math.min(min1,min2));
        }
        return result;
    }
}

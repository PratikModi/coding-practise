package com.java.coding.interviews.practise.razorpay;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of positive and negative numbers, find if there is a subarray (of size at-least one) with 0 sum.
 *
 * Examples :
 *
 * Input: {4, 2, -3, 1, 6}
 * Output: true
 * Explanation:
 * There is a subarray with zero sum from index 1 to 3.
 *
 * Input: {4, 2, 0, 1, 6}
 * Output: true
 * Explanation:
 * There is a subarray with zero sum from index 2 to 2.
 *
 * Input: {-3, 2, 3, 1, 6}
 * Output: false
 */
public class ZeroSubArraySumProblem {

    public static void main(String[] args) {
        int[] A = {-3, 2, 3, 1, 6};
        System.out.println(isZeroSumExist(A));
        A = new int[] {4, 2, 0, 1, 6};
        System.out.println(isZeroSumExist(A));
    }

    public static boolean isZeroSumExist(int[] A){
        if(A==null || A.length==0)
            return false;
        Set<Integer> previousSum = new HashSet<>();
        int sum=0;
        for(int i : A){
            sum+=i;
            if(i==0||sum==0||previousSum.contains(sum))
                return true;
            previousSum.add(sum);
        }
        return false;
    }

}

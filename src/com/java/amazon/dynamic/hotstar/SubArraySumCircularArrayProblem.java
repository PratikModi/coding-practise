package com.java.amazon.dynamic.hotstar;

/**
 * Maximum circular subarray sum
 * Difficulty Level : Hard
 * Last Updated : 21 May, 2021
 * Given n numbers (both +ve and -ve), arranged in a circle, find the maximum sum of consecutive numbers.
 *
 * Examples:
 *
 * Input: a[] = {8, -8, 9, -9, 10, -11, 12}
 * Output: 22 (12 + 8 - 8 + 9 - 9 + 10)
 *
 * Input: a[] = {10, -3, -4, 7, 6, 5, -4, -1}
 * Output:  23 (7 + 6 + 5 - 4 -1 + 10)
 *
 * Input: a[] = {-1, 40, -14, 7, 6, 5, -4, -1}
 * Output: 52 (7 + 6 + 5 - 4 - 1 - 1 + 40)
 */

public class SubArraySumCircularArrayProblem {

    public static void main(String[] args) {
        int[] A = {8, -8, 9, -9, 10, -11, 12};
        System.out.println(findSumArraySum(A));
    }

    public static int findSumArraySum(int[] A){
        if(A==null || A.length==0)
            return 0;
        if(A.length==1)
            return A[0];
        int sum=0;
        for(int a : A){
            sum+=a;
        }

        int current_max = A[0];
        int global_max = A[0];
        int current_min = A[0];
        int global_min = A[0];

        for(int i=1;i<A.length;i++){
            current_max = Math.max(current_max+A[i],A[i]);
            global_max = Math.max(global_max,current_max);

            current_min = Math.min(current_min+A[i],A[i]);
            global_min = Math.min(global_min,current_min);

            System.out.println(current_max+"**"+current_min+"**"+global_max+"**"+global_min);
        }

        if(global_min==sum)
            return global_max;
        System.out.println(global_max+"**"+(sum-global_min));
        return Math.max(global_max,sum-global_min);
    }

}

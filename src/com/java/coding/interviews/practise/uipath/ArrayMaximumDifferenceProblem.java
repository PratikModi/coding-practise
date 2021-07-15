package com.java.coding.interviews.practise.uipath;

/**
 * Maximum difference between two elements such that larger element appears after the smaller number
 * Difficulty Level : Medium
 * Last Updated : 03 Jun, 2021
 *
 * Given an array arr[] of integers, find out the maximum difference between any two elements such that larger element appears after the smaller number.
 *
 * Examples :
 *
 * Input : arr = {2, 3, 10, 6, 4, 8, 1}
 * Output : 8
 * Explanation : The maximum difference is between 10 and 2.
 *
 * Input : arr = {7, 9, 5, 6, 3, 2}
 * Output : 2
 * Explanation : The maximum difference is between 9 and 7.
 */
public class ArrayMaximumDifferenceProblem {

    public static void main(String[] args) {
        int[] A = {2, 3, 10, 6, 4, 8, 1};
        System.out.println(findMaximumDifference(A));
        A = new int[] {7, 9, 5, 6, 3, 2};
        System.out.println(findMaximumDifference(A));
    }
    //O(N) -- O(1)
    public static int findMaximumDifference(int[] A){
        int max_difference = 0;
        if(A==null || A.length<2)
            return max_difference;
        max_difference = A[1]-A[0];
        int min_element = A[0];
        int N = A.length;
        for(int i=1;i<N;i++){
            /*if(A[i]-min_element>max_difference){
                max_difference=A[i]-min_element;
            }*/
            max_difference = Math.max(max_difference,A[i]-min_element);
            min_element=Math.min(min_element,A[i]);
        }
        return max_difference;
    }

}

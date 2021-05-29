package com.java.coding.interviews.practise.microsoft;

import java.util.Arrays;

/**
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
 * Difficulty Level : Hard
 *  Last Updated : 02 Mar, 2021
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
 *
 * Examples :
 *
 *   Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
 *   Output: 6  (j = 7, i = 1)
 *
 *   Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
 *   Output: 8 ( j = 8, i = 0)
 *
 *   Input:  {1, 2, 3, 4, 5, 6}
 *   Output: 5  (j = 5, i = 0)
 *
 *   Input:  {6, 5, 4, 3, 2, 1}
 *   Output: -1
 */
public class ArrayMaxIndexDifferenceProblem {

    public static void main(String[] args) {
        int[] A = {34, 8, 10, 3, 2, 80, 30, 33, 1};
        System.out.println(findMaxDifference(A));
    }

    public static int findMaxDifference(int[] A){
        if(A==null || A.length<2)
            return 0;
        int maxDifference = Integer.MIN_VALUE;
        int N = A.length;
        int[] minArray = new int[N];
        int[] maxArray = new int[N];

        minArray[0]=A[0];
        for(int i=1;i<N;i++){
            minArray[i]=Math.min(minArray[i-1],A[i]);
        }
        maxArray[N-1]=A[N-1];
        for(int j=N-2;j>=0;j--){
            maxArray[j]=Math.max(maxArray[j+1],A[j]);
        }
        System.out.println(Arrays.toString(minArray));
        System.out.println(Arrays.toString(maxArray));
        int i=0,j=0;
        while(i<N && j<N){
            if(minArray[i]<=maxArray[j]){
                maxDifference=Math.max(maxDifference,j-i);
                j++;
            }else
                i++;
        }
        return maxDifference;
    }

}

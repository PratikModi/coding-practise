package com.java.coding.interviews.practise.adobe;

import java.util.Arrays;

/**
 * Find First and Last Position of Element in Sorted Array
 * Medium
 *
 * 5746
 *
 * 209
 *
 * Add to List
 *
 * Share
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FirstLastInSortedArrayProblem {
    public static void main(String[] args) {
        int[] A = {5,7,7,8,8,10};
        int target =8;
        int[] result = searchRange(A,target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] searchRange(int[] A, int target){
        int[] result = {-1,-1};
        if(A==null || A.length==0)
            return result;
        int low = 0;
        int high = A.length-1;
        result[0]=first(A,low,high,target);
        result[1]=last(A,low,high,target);
        return result;
    }

    private static int first(int[] A, int low, int high, int target){
        int result=-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(A[mid]>target){
                high=mid-1;
            }else if(A[mid]<target){
                low=mid+1;
            }else{
                result=mid;
                high=mid-1;
            }
        }
        return result;
    }


    private static int last(int[] A, int low, int high,int target){
        int result=-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(A[mid]>target){
                high=mid-1;
            }else if(A[mid]<target){
                low=mid+1;
            }else{
                result=mid;
                low=mid+1;
            }
        }
        return result;
    }

}

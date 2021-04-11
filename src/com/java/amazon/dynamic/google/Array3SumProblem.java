package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 25-06-2020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums
 * such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 Note:

 The solution set must not contain duplicate triplets.
 Example:
 Given array nums = [-1, 0, 1, 2, -1, -4],
 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */
public class Array3SumProblem {

    public static void main(String[] args) {
        int[] A = {-1, 0, 1, 2, -1, -4};
        System.out.println(findTriplet(A));
    }

    public static List<List<Integer>> findTriplet(int[] A){
        if(A==null || A.length<3)
            return null;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(A);
        for(int i=0;i<A.length-2;i++) {
            //System.out.println(A[i]-1);
            if(i>0 && (long)A[i-1]==(long)A[i]){
                continue;
            }
            long target = -(long)A[i];
            int left = i+1;
            int right = A.length - 1;
            helper(A,result,target,left,right);
        }
        return result;
    }

    private static void helper(int[] A, List<List<Integer>> result, long target, int left,int right){
        while(left<right){
            long sum = (long)A[left]+(long)A[right];
            if(sum==target) {
                result.add(Arrays.asList(-(int)target, A[left], A[right]));
                left++;
                right--;
                while (left < right && A[left] == A[left - 1])
                    left++;
                while (left < right && A[right] == A[right + 1])
                    right--;
            }else if(sum <= target){
                left++;
            }else{
                right--;
            }
        }
    }
}


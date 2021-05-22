package com.java.amazon.dynamic.adobe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class Array4SumProblem {
    public static void main(String[] args) {
        int[] A = {1,0,-1,0,-2,2};
        int target=0;
        System.out.println(find4Sum(A,target));
        A= new int[] {2,2,2,2,2};
        target=8;
        System.out.println(find4Sum(A,target));
    }

    public static List<List<Integer>> find4Sum(int[] A, int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(A);
        int N = A.length;
        for(int i=0;i<N-3;i++){
            if(i>0 && A[i]==A[i-1])
                continue;
            for(int j=i+1;j<N-2;j++){
                if(j>i+1 && A[j]==A[j-1])
                    continue;
                int L = j+1;
                int R = N-1;
                while(L<R){
                    int sum = A[i]+A[j]+A[L]+A[R];
                    if(sum==target){
                        List<Integer> quad = new ArrayList<>();
                        quad.add(A[i]);
                        quad.add(A[j]);
                        quad.add(A[L]);
                        quad.add(A[R]);
                        result.add(quad);
                        L++;
                        R--;
                        while(L<N && A[L-1]==A[L]) //Skip Duplicate
                            L++;
                        while(R>0 && A[R]==A[R+1])
                            R--;
                    }else if(sum<target) {
                        L++;
                    }else{
                        R--;
                    }
                }
            }
        }
        return result;
    }
}

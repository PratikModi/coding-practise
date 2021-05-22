package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class Array3SumClosestProblem {

    public static void main(String[] args) {
        int [] nums = {-1,2,1,-4};
        int result = closestSum(nums,1);
        System.out.println(result);
    }

    public static int closestSum(int[] nums, int target){
        if(nums==null || nums.length==0)
            return 0;
        Arrays.sort(nums);
        int result=0;
        int N = nums.length;
        int gap = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            if(i==0 || nums[i]!=nums[i-1]) {
                int _1sum = nums[i];
                int left = i+1;
                int right = N-1;
                while(left<right){
                    int _3sum=_1sum+nums[left]+nums[right];
                    if(Math.abs(_3sum-target)<gap){
                        result = _3sum;
                        gap=Math.abs(_3sum-target);
                    }
                    if(_3sum>target){
                        int temp = nums[right];
                        while(right>0 && nums[right]==temp){
                            right--;
                        }
                    }else if(_3sum<target){
                        int temp = nums[left];
                        while(left<N-1 && nums[left]==temp){
                            left++;
                        }
                    }else{
                        return target;
                    }
                }
            }
        }
        return result;
    }

}

package com.java.coding.interviews.practise.bloomberg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSumProblem {

    public static void main(String[] args) {
        var result = twoSum(new int[]{2,7,11,15},9);
        System.out.println(Arrays.toString(result));
        result = twoSum(new int[]{3,2,4},6);
        System.out.println(Arrays.toString(result));
        result = twoSum(new int[]{3,3},6);
        System.out.println(Arrays.toString(result));
    }


    public static int[] twoSum(int[] nums, int target){
        if(nums==null || nums.length==0)
            return nums;
        Map<Integer,Integer> map = new HashMap<>();
        int[] pair = new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                pair[0]=map.get(nums[i]);
                pair[1]=i;
            }else{
                map.put(target-nums[i],i);
            }
        }
        return pair;
    }

}

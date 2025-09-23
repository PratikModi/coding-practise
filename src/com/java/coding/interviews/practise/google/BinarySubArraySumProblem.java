package com.java.coding.interviews.practise.google;

/**
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 */
//Time Complexity: O(n)
//Space Complexity: O(1)
public class BinarySubArraySumProblem {

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,1,0,1};
        int goal=2;
        System.out.println(findNumberSubarrayWithSum(nums,goal));
        nums = new int[]{0,0,0,0,0};
        goal = 0;
        System.out.println(findNumberSubarrayWithSum(nums,goal));
    }

    public static int findNumberSubarrayWithSum(int[] nums, int goal){
        return atMost(nums,goal) - atMost(nums,goal-1);
    }

    private static int atMost(int[] nums, int goal){
        if(goal<0) return 0;
        int left=0;
        int count=0;
        int sum=0;
        for(int right=0;right<nums.length;right++){
            sum+=nums[right];
            while(sum>goal){
                sum-=nums[left++]; // Shrink the window
            }
            count+=right-left+1;
        }
        return count;
    }

}

package com.java.coding.interviews.practise.salesforce;

/**
 * LeetCode : 213. House Robber II
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 ⸻

 🧠 Problem Summary
 •	Houses arranged in a circle.
 •	If you rob house 0, you cannot rob house n-1.
 •	So you must choose between two cases:

 Case 1: Rob houses from 0 to n-2

 Case 2: Rob houses from 1 to n-1

 Take the maximum of both.

 This reduces the problem to House Robber I twice.

 ✅ Java Code (Optimal DP, O(n))
 */
public class HouseRobberIIProblem {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(rob(nums));
        nums = new int[]{1,2,3,1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        if(n==2) return Math.max(nums[0],nums[1]);
        int case1 = rob(nums,0,n-2);
        int case2 = rob(nums,1,n-1);
        return Math.max(case1, case2);
    }

    private static int rob(int[] nums, int start, int end){
        int prev1=0; //dp[i-1]
        int prev2=0; //dp[i-2]

        for(int i=start;i<=end;i++){
            int pick = prev2+nums[i];
            int skip = prev1;
            int curr = Math.max(pick,skip);
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
}

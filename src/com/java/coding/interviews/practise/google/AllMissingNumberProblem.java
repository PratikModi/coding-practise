package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 *
 *
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */
public class AllMissingNumberProblem {

    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(findDisappearedNumbers(nums));
        nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> missingNumber = new ArrayList<>();
        int n = nums.length;
        for(int i=0;i<n;i++) {
            int value = Math.abs(nums[i]);
            if (nums[value-1] > 0) {
                nums[value - 1] = -nums[value - 1];
            }
        }
        for(int i=0;i<n;i++){
            if(nums[i]>0) {
                missingNumber.add(i+1);
            }
        }
        return missingNumber;
    }


}

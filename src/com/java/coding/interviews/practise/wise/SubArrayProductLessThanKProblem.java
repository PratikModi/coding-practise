package com.java.coding.interviews.practise.wise;

/**
 * LeetCode:713. Subarray Product Less Than K
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the
 * product of all the elements in the subarray is strictly less than k.
 *
 * Example 1:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 *
 * ⏱️ Time Complexity
 * 	•	O(N) — each element is added and removed from the sliding window at most once.
 * 	•	O(1) extra space.
 */
public class SubArrayProductLessThanKProblem {

    public static void main(String[] args) {
        int[] nums = new int[]{10,5,2,6};
        int k=100;
        System.out.println(numSubarrayProductLessThanK(nums,k));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1) return 0;
        int count=0;
        int left=0;
        int product=1;
        for(int right=0;right<nums.length;right++){
            product*=nums[right];
            while(product>=k){
                product/=nums[left];
                left++;
            }
            count+=(right-left+1);
        }
        return count;
    }

}

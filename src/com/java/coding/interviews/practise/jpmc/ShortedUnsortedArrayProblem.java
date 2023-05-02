package com.java.coding.interviews.practise.jpmc;

/**
 * Shortest Unsorted Continuous Sub array
 * Given an integer array nums, you need to find one continuous sub array such that if you only sort this sub array in non-decreasing order,
 * then the whole array will be sorted in non-decreasing order.
 *
 * Return the shortest such sub array and output its length.
 *
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 0
 */
public class ShortedUnsortedArrayProblem {

    public static void main(String[] args) {
        int[] nums = {2,6,4,8,10,9,15};
        System.out.println(findUnsortedSubArray(nums));
        nums = new int[]{1,2,3,4};
        System.out.println(findUnsortedSubArray(nums));
        nums = new int[]{1};
        System.out.println(findUnsortedSubArray(nums));
    }
    //Time Complexity:  O(N)
    //Space Complexity: O(1)
    public static int findUnsortedSubArray(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int N = nums.length;
        // Finding the first and last position of the unsorted sub array
        for(int i=1;i<N;i++){
            if(nums[i]<nums[i-1]){
                minValue = Math.min(minValue,nums[i]);
            }
        }
        for(int j=N-2;j>=0;j--){
            if(nums[j]>nums[j+1]){
                maxValue=Math.max(maxValue,nums[j]);
            }
        }
        //System.out.println(minValue+"--"+maxValue);
        if(minValue==Integer.MAX_VALUE && maxValue==Integer.MIN_VALUE)
            return 0;
        int start=0, end=N-1;
        // find index in the left for a number greater than the min value found
        for(;start<N;start++){
            if(nums[start]>minValue) break;
        }
        // find index in the right for a number lesser than the max value found
        for(;end>=0;end--){
            if(nums[end]<maxValue) break;
        }
        return end-start+1;
    }

}

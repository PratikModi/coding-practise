package com.java.coding.interviews.practise.deliveroo;

/**
 * 1248. Count Number of Nice Subarrays
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 */
public class NiceSubArrayCountProblem {

    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int k = 3;
        System.out.println(numberOfSubarrays(nums,k));
    }
    //Time Complexity: O(n)
    private static int numberOfSubarrays(int[] nums, int k){
        return atMost(nums,k) - atMost(nums,k-1);
    }

    private static int atMost(int[] nums, int k){
        int left=0;
        int count=0;
        int odd=0;

        for(int right=0;right<nums.length;right++){
            if(nums[right]%2==1){
                odd++;
            }
            while(odd>k){
                if(nums[left]%2==1){
                    odd--;
                }
                left++;
            }
            count+=right-left+1;
        }
        return count;
    }

}

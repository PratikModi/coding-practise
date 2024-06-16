package com.java.coding.interviews.practise.arcesium;

/**
 * 1567. Maximum Length of Sub array With Positive Product
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest sub array with positive product is [-1,-2] or [-2,-3].
 */
public class MaxArrayWithPositiveProduct {

    public static void main(String[] args) {
        int[] nums = new int[]{1,-2,-3,4};
        System.out.println(maxLen(nums));
        nums = new int[]{0,1,-2,-3,-4};
        System.out.println(maxLen(nums));
        nums = new int[]{-1,-2,-3,0,1};
        System.out.println(maxLen(nums));
    }

    public static int maxLen(int[] nums){
        int positive=0,negative=0;
        int result=0;
        for(int x:nums){
            if(x==0){
                positive=0;
                negative=0;
            }else if(x>0){
                positive++;
                negative=negative==0?0:negative+1;
            }else{
                int temp=positive;
                positive=negative==0?0:negative+1;
                negative=temp+1;
            }
            result = Math.max(result,positive);
        }
        return result;
    }

}

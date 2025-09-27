package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. Contains Duplicate II
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
 * such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class ContainsDuplicateProblem {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int k=3;
        System.out.println(containsNearbyDuplicate(nums,k));
        nums = new int[] {1,0,1,1};
        k=1;
        System.out.println(containsNearbyDuplicate(nums,k));
        nums = new int[]{1,2,3,1,2,3};
        k=2;
        System.out.println(containsNearbyDuplicate(nums,k));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length<2) return false;
        Map<Integer,Integer> seen = new HashMap<>();
        for(int i=0;i< nums.length;i++){
            if(seen.containsKey(nums[i]) && i-seen.get(nums[i])<=k){
                return true;
            }
            seen.put(nums[i],i);
        }
        return false;
    }

}

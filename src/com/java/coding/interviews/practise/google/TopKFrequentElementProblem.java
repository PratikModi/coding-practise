package com.java.coding.interviews.practise.google;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 *
 * Output: [1,2]
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 *
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 *
 * Output: [1,2]
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElementProblem {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k =2;
        System.out.println(Arrays.toString(topKFrequentElement(nums,k)));
    }

    private static int[] topKFrequentElement(int[] nums, int k){
        Map<Integer,Integer> freqMap = new HashMap<>();
        for(int num : nums){
            freqMap.put(num,freqMap.getOrDefault(num,0)+1);
        }
        List<Integer>[] bucket = new List[nums.length+1];
        for(int i=0;i<=nums.length;i++){
            bucket[i]=new ArrayList<>();
        }
        for(int num : freqMap.keySet()){
            bucket[freqMap.get(num)].add(num);
        }
        int[] result = new int[k];
        int index=0;
        for(int i=bucket.length-1;i>=0 && index<k;i--){
            for(int num : bucket[i]){
                result[index++]=num;
            }
        }
        return result;
    }

}

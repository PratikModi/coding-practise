package com.java.coding.interviews.practise.salesforce;

import java.util.*;

/**
 * LeetCode: 347. Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 *
 * 🕒 Time Complexity
 * 	•	Counting frequencies: O(n)
 * 	•	Filling buckets: O(n)
 * 	•	Scanning buckets: O(n)
 * ➡️ Total: O(n)
 *
 * 📦 Space Complexity
 * 	•	HashMap: O(n)
 * 	•	Buckets: O(n)
 * ➡️ Total: O(n)
 *
 * ✅ Approach: Bucket Sort (Time: O(n), Space: O(n))
 *
 * Steps:
 * 	1.	Count frequency of each number using a HashMap.
 * 	2.	Create an array of buckets where:
 * 	bucket[freq] = list of numbers with that frequency
 * 	3.	Iterate buckets from highest freq → lowest, collecting k elements.
 */
public class TopKFrequentElementProblem {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k=1;
        System.out.println(Arrays.toString(topKFrequent(nums,k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freqMap = new HashMap<>();
        for(int num : nums){
            freqMap.put(num,freqMap.getOrDefault(num,0)+1);
        }
        List<Integer>[] buckets = new List[nums.length+1];
        for(int num : freqMap.keySet()){
            int freq = freqMap.get(num);
            if(buckets[freq]==null){
                buckets[freq]=new ArrayList<>();
            }
            buckets[freqMap.get(num)].add(num);
        }
        int[] result = new int[k];
        int index=0;
        for(int i=buckets.length-1;i>=0&&k>index;i--){
            if(buckets[i]!=null) {
                for (int num : buckets[i]) {
                    result[index++] = num;
                    if(index==k) break;
                }
            }
        }
        return result;
    }
}

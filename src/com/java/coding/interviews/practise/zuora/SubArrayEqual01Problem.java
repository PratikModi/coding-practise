package com.java.coding.interviews.practise.zuora;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Subarray With Equal Number of 0s and 1s
 * Given an array arr[] containing only 0s and 1s, find the longest subarray which contains equal no of 0s and 1s.
 *
 * Examples:
 *
 * Input: arr[] = [1, 0, 1, 1, 1, 0, 0]
 * Output: 6
 * Explanation: arr[1 ... 6] is the longest subarray with three 0s and three 1s.
 *
 * Input: arr[] = [0, 0, 1, 1, 0]
 * Output: 4
 * Explanation: arr[0 ... 3] or  arr[1 ... 4] is the longest subarray with two 0s and two 1s.
 *
 * Input: arr[] = [0]
 * Output: 0
 * Explanation: There is no subarray with an equal number of 0s and 1s.
 *
 * 💡 Intuition
 *
 * We want equal number of 0s and 1s.
 * → Replace all 0s with -1.
 * Now the problem becomes:
 * Find the longest subarray with sum = 0.
 * Because:
 * 	•	1 → +1
 * 	•	0 → -1
 * 	•	Equal numbers of 1s and -1s → sum = 0
 *
 * ✅ Approach (Prefix Sum + HashMap)
 * 	1.	Initialize a sum = 0 and maxLen = 0.
 * 	2.	Use a HashMap to store the first index where a particular prefix sum occurs.
 * 	•	Key = prefix sum
 * 	•	Value = index
 * 	3.	Iterate through the array:
 * 	•	Replace 0 with -1.
 * 	•	Add to prefix sum.
 * 	•	If sum = 0 → subarray from 0 to i is balanced.
 * 	•	If the same sum was seen before → subarray between the two indices is balanced.
 * 	•	Update maxLen accordingly.
 *
 * 🧠 Complexity
 * Type         Complexity        Reason
 * Time             O(n)             Single pass through array
 * Space           O(n)             HashMap stores prefix sums
 */
public class SubArrayEqual01Problem {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 1, 0, 0};
        System.out.println(maxLength(nums));
    }

    public static int maxLength(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        int sum=0;
        int maxLen=0;
        // Initialize: sum 0 occurs at index -1
        map.put(0,-1);

        for (int i = 0; i < nums.length; i++) {
            sum+= nums[i]==0?-1:1;
            if(map.containsKey(sum)){
                maxLen = Math.max(maxLen,i-map.get(sum));
        }else{
                map.put(sum,i);
            }
        }
        return maxLen;
    }

}

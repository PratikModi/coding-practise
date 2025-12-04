package com.java.coding.interviews.practise.salesforce;

/**
 * LeetCode: 740. Delete and Earn
 * You are given an integer array nums. You want to maximize the number of points you get by performing the
 * following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1
 * and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 *
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 * Approach:
 * ========
 * 🔵 Step 1— Find the maximum value in nums
 *  Why?
 * 	•	Our DP array will run from 0 to max.
 * 	•	We need to know how many DP entries to create.
 * 	🔵 Step 2 — Build the earn[] array
 * 	Why?
        * Every time you pick value x, you earn x for each occurrence.
 *  🔵 Step 3 — House Robber DP variables
 *  dp[i] = max(
 *     dp[i-1],        // skip i
 *     dp[i-2] + earn[i]  // take i
 * )
 * We only need the last two states, so we store them as:
 * 	•	prev1 = dp[i-1]
 * 	•	prev2 = dp[i-2]
 * 	🔵 Step 5 — DP loop from 0 to max
 *
 * 	✔ Final Answer
 * 	Version             Time Complexity             Space Complexity
 * Normal DP         O(n + maxValue)              O(maxValue)
 */
public class DeleteAndEarnProblem {
    public static void main(String[] args) {
        int[] nums = {3,4,2};
        System.out.println(deleteAndEarn(nums));
    }

    public static int deleteAndEarn(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int max =0;
        // Find the max value
        for(int num : nums){
            max = Math.max(max,num);
        }
        //build the earn array
        int[] earn = new int[max+1];
        for(int num : nums){
            earn[num]+=num;
        }

        int prev1=0;
        int prev2=0;
        int result=0;
        for(int i=0;i<=max;i++){
            int curr = Math.max(prev1, prev2+earn[i]);
            prev2=prev1;
            prev1=curr;
        }
        return prev1; //max value
    }
}

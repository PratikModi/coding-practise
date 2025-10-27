package com.java.coding.interviews.practise.zuora;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode: 496. Next Greater Element I
 *
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and
 * determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 * 💡 Intuition
 *
 * We can preprocess nums2 using a monotonic decreasing stack to find the “next greater element” for each number efficiently.
 * Then we map each element in nums2 → its next greater value using a HashMap
 * and finally answer queries for nums1.
 *
 * ⏱ Time & Space Complexity
 * Operation                          Complexity                         Explanation
 * Traverse nums2                  O(n)                                 Each element pushed/popped once
 * Build result for nums1         O(m)                                Lookup from map
 * Total Time                          O(n + m)                         Linear
 * Space                                O(n)                                Stack + HashMap
 */
public class NextGreaterElementProblem {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1,nums2)));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer,Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i=1;i<nums2.length;i++){
            if(nums2[i]>nums2[stack.peek()]){
                while(!stack.isEmpty() && nums2[i]>nums2[stack.peek()]){
                    nextGreater.put(nums2[stack.pop()],nums2[i]);
                }
            }
            stack.push(i);
        }

        int[] result = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            result[i]=nextGreater.getOrDefault(nums1[i],-1);
        }
        return result;
    }

}

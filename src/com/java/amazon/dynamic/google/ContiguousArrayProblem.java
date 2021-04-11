package com.java.amazon.dynamic.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous sub array with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous sub array with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous sub array with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArrayProblem {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,0,0,1};
        System.out.println(findMaxLength(nums));
        System.out.println(findMaxLength(new int[]{1,0,1,0,1,0,1,1,0,1,1}));
    }

    public static int findMaxLength(int[] nums) {
        int result=0;
        if(nums==null || nums.length==0)
            return result;
        Map<Integer,Integer> sumIndex = new HashMap<>();
        int sum=0;
        int max_so_far=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                sum+=-1;
            }else{
                sum+=1;
            }
            if(sum==0){
                max_so_far=i+1;
                result = Math.max(result,max_so_far);
            }
            if(sumIndex.containsKey(sum)){
                max_so_far=i - sumIndex.get(sum);
                result = Math.max(result,max_so_far);
            }else{
                sumIndex.put(sum,i);
            }
        }
        return result;
    }


}

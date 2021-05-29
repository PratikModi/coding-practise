package com.java.coding.interviews.practise.hotstar;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 */
public class FlipZeroOneProblem {
    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max=0;
        int start=0;
        int end=0;
        int zeroCount=0;
        int lastZero=-1;
        int N = nums.length;
        while(end<N){
            if(nums[end]==0) {
                zeroCount++;
                if (zeroCount > 1) {
                    max = Math.max(max, end - start);
                    zeroCount--;
                    start = lastZero + 1;
                }
                lastZero = end;
            }
            end++;
        }
        return Math.max(max,end-start);
    }


}

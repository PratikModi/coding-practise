package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 */
public class FirstMissingPositiveProblem {
    public static void main(String[] args) {
        int[] A = {3,4,-1,1};
        System.out.println(findFirstMissingPositive(A));
        A = new int[] {1,2,0};
        System.out.println(findFirstMissingPositive(A));
        A = new int[] {2,3,4};
        System.out.println(findFirstMissingPositive(A));
        System.out.println(findFirstMissingPositive(A));
        A = new int[] {1};
        System.out.println(findFirstMissingPositive(A));

    }

    public static int findFirstMissingPositive(int[] nums){
        if(nums==null || nums.length==0)
            return 1;
        int foundOne=0;
        int N = nums.length;
        for(int i=0;i<N;i++){
            if(nums[i]==1)
                foundOne=1;
            if(nums[i]<=0 || nums[i]>N)
                nums[i]=1;
        }
        if(foundOne==0)
            return 1;
        System.out.println(Arrays.toString(nums));
        for(int i=0;i<N;i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index]>0) nums[index]=-1*nums[index];
        }
        System.out.println(Arrays.toString(nums));
        for(int i=0;i<N;i++){
            if(nums[i]>0){
                return i+1;
            }
        }
        return N+1;
    }

    public int firstMissingPositive(int[] nums) {

        PriorityQueue<Integer> queue = new PriorityQueue();
        for(int i =0;i<nums.length;i++){
            if(nums[i] >=0)
                queue.offer(nums[i]);
        }

        int result=0;
        while(!queue.isEmpty()){
            if(queue.peek()==result || queue.peek()-1==result)
                result = queue.poll();
            else
                return result+1;
        }

        return result+1;

    }

}

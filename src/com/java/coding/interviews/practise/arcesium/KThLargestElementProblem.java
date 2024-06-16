package com.java.coding.interviews.practise.arcesium;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
public class KThLargestElementProblem {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(kthLargest(nums,2));
        nums=new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(kthLargest(nums,4));
    }

    public static int kthLargest(int[] nums, int k){
        if(nums==null || nums.length==0 || k==0 || nums.length<k)
            return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int x : nums){
            pq.offer(x);
        }
        for(int i=0;i<k-1;i++){
            pq.remove();
        }
        return pq.peek();
    }

}

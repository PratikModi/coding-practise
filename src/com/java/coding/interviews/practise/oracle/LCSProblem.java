package com.java.coding.interviews.practise.oracle;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LCSProblem {

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(findLCS(nums));
        System.out.println(findLCS2(nums));
        nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(findLCS(nums));
        System.out.println(findLCS2(nums));
    }

    public static int findLCS(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        int result=0;
        Set<Integer> numSet = new HashSet<>();
        for(int n : nums){
            numSet.add(n);
        }
        for(int i : nums){
            int n=i;
            int n1=n+1;
            int n2=n-1;
            while(numSet.contains(n1)){
                numSet.remove(n1);
                n1++;
            }
            while(numSet.contains(n2)){
                numSet.remove(n2);
                n2--;
            }
            result = Math.max(result,n1-n2-1);
        }
        return result;
    }

    public static int findLCS2(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        int result=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> a-b);
        for(int n : nums){
            pq.add(n);
        }
        int counter=1;
        int  previous = pq.poll();
        for(int i=1;i<nums.length;i++){
            if(pq.peek()-previous>1){
                counter=1;
                previous=pq.poll();
            }else if(pq.peek()-previous==0){
                previous=pq.poll();
            }else{
                counter++;
                previous=pq.poll();
            }
            result=Math.max(result,counter);
        }

        return result;
    }

}

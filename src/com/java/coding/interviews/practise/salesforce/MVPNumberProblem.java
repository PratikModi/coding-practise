package com.java.coding.interviews.practise.salesforce;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 𝐌𝐕𝐏'𝐬 𝐢𝐧 𝐚𝐧 𝐀𝐫𝐫𝐚𝐲
 * Given an array A of positive integers. Your task is to find the MVP's in the array.
 * An element of array is considered MVP if it is greater than or equal to all the elements to its right side.
 * The rightmost element is always a MVP.
 * Input:
 * n = 6
 * A[] = {16,17,4,3,5,2} Output: 17 5 2
 * Explanation:
 * The first MVP is 17 as it is greater than all the elements to its right.
 * Similarly, the next leader is 5 followed by 2.
 * 𝗖𝗼𝗻𝘀𝘁𝗿𝗮𝗶𝗻𝘁𝘀:-
 * 1 <= n <= 10^7
 * 0 <= Ai <= 10^7
 */
public class MVPNumberProblem {

    public static void main(String[] args) {
        int[] nums = new int[]{16,17,4,3,5,2};
        System.out.println(findMVP(nums));
    }


    public static List<Integer> findMVP(int[] nums){
        List<Integer> result = new ArrayList<>();
        if(nums==null || nums.length==0)
            return result;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums.length;i++){
            if(stack.isEmpty() || nums[i]<stack.peek()){
                stack.push(nums[i]);
            }else{
                while(!stack.isEmpty() && stack.peek()<nums[i]){
                    stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        result.addAll(stack);
        return result;
    }

}

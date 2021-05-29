package com.java.coding.interviews.practise.hotstar;

import java.util.*;

/**
 * Given an array, print all subarrays in the array which has sum 0.
 *
 * Examples:
 *
 * Input:  arr = [6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7]
 * Output:
 * Subarray found from Index 2 to 4
 * Subarray found from Index 2 to 6
 * Subarray found from Index 5 to 6
 * Subarray found from Index 6 to 9
 * Subarray found from Index 0 to 10
 */

public class SubArrayWithSumZeroProblem {

    public static void main(String[] args) {
        System.out.println(findSubArray(new int[]{3, 4, -7, 3, 1, 3, 1, -4, -2, -2}));
    }

    public static List<List<Integer>> findSubArray(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer,List<Integer>> sumIndex= new HashMap<>();
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum==0){
                result.add(Arrays.asList(0,i));
            }
            if(sumIndex.containsKey(sum)){
                List<Integer> indexes = sumIndex.get(sum);
                for(int index : indexes){
                    result.add(Arrays.asList(index+1,i));
                }
            }
            sumIndex.putIfAbsent(sum,new ArrayList<Integer>());
            sumIndex.get(sum).add(i);
        }
        System.out.println(sumIndex);
        return result;
    }
}

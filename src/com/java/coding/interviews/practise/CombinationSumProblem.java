package com.java.coding.interviews.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class    CombinationSumProblem {

    public static List<List<Integer>> findCombinations(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length==0)
            return result;
        Arrays.sort(nums);
        helper(result,0,nums,target, new ArrayList<Integer>());
        return result;
    }

    private static void helper(List<List<Integer>> result,int index,int[] nums, int target, List<Integer> combination){
        if(target==0 && !result.contains(combination)){
            result.add(new ArrayList<>(combination));
        }
        for(int i=index;i<nums.length;i++){
            if(nums[i]>target)
                break;
            combination.add(nums[i]);
            helper(result,i,nums,target-nums[i],combination);
            combination.remove(combination.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(findCombinations(new int[]{8, 10, 6, 11, 1, 16, 8},8));
    }
}

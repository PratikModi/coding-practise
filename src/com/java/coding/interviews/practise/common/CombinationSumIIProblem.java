package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class CombinationSumIIProblem {

    public static List<List<Integer>> findCombinations(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(candidates==null || candidates.length==0)
            return result;
        Arrays.sort(candidates);
        helper(result,candidates,target,0,new ArrayList<Integer>());
        return result;
    }

    private static void helper(List<List<Integer>> result, int[] candidates, int target,int index, List<Integer> combination){
        if(target==0){
            result.add(new ArrayList<>(combination));
            return;
        }
        if(target<0)
            return;
        for(int i=index;i<candidates.length;i++){
            if(i==0||candidates[i]!=candidates[i-1]) {
                if (candidates[i] > target)
                    break;
                combination.add(candidates[i]);
                helper(result, candidates, target - candidates[i], i + 1, combination);
                combination.remove(combination.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findCombinations(new int[]{2,5,3,8},8));
    }
}

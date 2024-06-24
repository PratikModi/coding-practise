package com.java.coding.interviews.practise.intuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of size n, generate and print all possible combinations of r elements in the array.
 *
 * Example:
 *
 * Input: arr=[1,2,3,4], r=2
 *
 * Output: 1 2
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 3 4
 *
 * Input: arr=[1,2,3,4], r=3
 *
 * Output: 1 2 3
 * 1 2 4
 * 1 3 4
 * 2 3 4
 */
public class DistinctCombinationProblem {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        int k=3;
        System.out.println(findCombinations(nums,k));
    }

    public static void util(int[] nums, int n, int k, int index, Set<List<Integer>> result, List<Integer> combination){
        if(k==0){
            result.add(new ArrayList<>(combination));
            return;
        }
        for(int j=index;j<n;j++) {
            combination.add(nums[j]);
            util(nums, n, k-1, j + 1,  result, combination); // include next element
            combination.remove(combination.size()-1); //backtrack
        }
    }

    public static Set<List<Integer>> findCombinations(int[] nums, int k){
        Set<List<Integer>> result = new HashSet<>();
        util(nums,nums.length,k,0,result,new ArrayList<Integer>());
        return result;
    }

}

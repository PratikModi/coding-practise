package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pratik1 on 21-02-2020.
 */
public class PowerSet {

    private static void powerSet(String s){
        powerSet(s,0,"");
    }

    private static void powerSet(String s, int index, String current){
        int n=s.length();
        if(index>n){
            return;
        }
        System.out.println(current);
        for(int i=index;i<n;i++){
            current+=s.charAt(i);
            powerSet(s,i+1,current);
            current=current.substring(0,current.length()-1);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if(nums==null || nums.length==0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        Arrays.sort(nums);
        helper(result,nums,new ArrayList<Integer>(),0);
        return result;
    }

    private static void helper(List<List<Integer>>result, int[] nums, ArrayList<Integer> subset, int index){
        int N = nums.length;
        if(index==N){
            result.add(new ArrayList(subset));
            return;
        }
        result.add(new ArrayList<>(subset));
        List<Integer> used = new ArrayList<>();
        for(int i=index;i<N;i++){
            if(!used.contains(nums[i])) {
                subset.add(nums[i]);
                used.add(nums[i]);
                helper(result, nums, subset, i + 1);
                //System.out.println(i + "==>"+subset+"==>"+Arrays.toString(nums));
                subset.remove(subset.indexOf(nums[i]));
            }
        }
    }




    public static void main(String[] args) {
        //powerSet("abc");
        System.out.println(subsets(new int[] {1,2,2}));
    }
}

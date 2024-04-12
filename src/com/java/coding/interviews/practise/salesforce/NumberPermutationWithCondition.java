package com.java.coding.interviews.practise.salesforce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Question 1: Given an integer n, return the number of valid permutations that can be constructed based on the below criteria where either of the following should be true:
 * A. permutation[i] is divisible by i B. i is divisible by permutation[]
 * For example, it n = 2 then the possible permutations are[[1,2] [2,1]] and output will be 2 as 2 different permutations satisfies above conditions
 */
public class NumberPermutationWithCondition {

    public static void main(String[] args) {
        var result = findPermutations(3);
        System.out.println(result);
    }

    public static List<List<Integer>> findPermutations(int n){
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(permutations, IntStream.range(1,n+1).boxed().collect(Collectors.toList()), 0, n-1);
        return permutations;
    }

    public static void backtrack(List<List<Integer>> permutations, List<Integer> permutation, int left, int right){
        if(left==right){
            if(isValid(permutation))
                permutations.add(new ArrayList<>(permutation));
            return;
        }else{
            for(int i=left;i<=right;i++){
                swap(permutation,i,left);
                backtrack(permutations,permutation,left+1,right);
                swap(permutation,i,left);
            }
        }
    }

    private static void swap(List<Integer> list, int left, int right){
        int temp = list.get(left);
        list.set(left,list.get(right));
        list.set(right,temp);
    }

    public static boolean isValid(List<Integer> permutation){
        for(int i=0;i<permutation.size();i++){
            if(permutation.get(i)%(i+1)!=0 && (i+1)%permutation.get(i)!=0)
                return false;
        }
        return true;
    }



}

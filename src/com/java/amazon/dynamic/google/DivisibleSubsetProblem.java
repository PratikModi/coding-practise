package com.java.amazon.dynamic.google;

import java.util.*;

/**
 * This problem was asked by Google.
 *
 * Given a set of distinct positive integers, find the largest subset such that every pair
 * of elements in the subset (i, j) satisfies either i % j = 0 or j % i = 0.
 *
 * For example, given the set [3, 5, 10, 20, 21], you should return [5, 10, 20].
 * Given [1, 3, 6, 24], return [1, 3, 6, 24].
 */

public class DivisibleSubsetProblem {

    public static void main(String[] args) {
        int[] A = new int[] {1,3,6,24};
        System.out.println(findDivisibleSubset(A));
        A = new int[]{3,5,10,20,21};
        System.out.println(findDivisibleSubset(A));
        A = new int[]{1,2,3};
        System.out.println(findDivisibleSubset(A));
    }

    public static List<Integer> findDivisibleSubset(int[] nums){
        if(nums==null || nums.length==0){
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int N = nums.length;
        int max=1;
        int[] DP = new int[N];
        Arrays.fill(DP,1);
        Arrays.sort(nums);
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0 && DP[i]<1+DP[j]){
                    DP[i]=1+DP[j];
                    max=Math.max(max,DP[i]);
                }
            }
        }
        System.out.println(Arrays.toString(DP));
        Set<Integer> set = new TreeSet<>();
        int previous=-1;
        for(int i=N-1;i>=0;i--){
            if(DP[i]==max && (previous%nums[i]==0 || previous==-1)){
                set.add(nums[i]);
                max--;
                previous=nums[i];
            }
        }
        result.addAll(set);
        return result;
    }

}

package com.java.coding.interviews.practise.google;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an input array, find the largest sum of a subset that does not contain neighboring values.
 * A neighboring value is defined as this: for number n, value n-1, n+1 are its neighboring values. For example, 2 is the neighboring value of 1, 3.
 * For example, given input {1,1,2,2,2,3,3,3,6}, the largest subset that does not contain neighboring values is {1,1,3,3,3,6}, and sum is 17.
 */

public class ArrayMaxSumNonAdjacentProblem {

    public static void main(String[] args) {
        int[] A = new int[]{1,1,2,2,2,3,3,3,6};
        int result = findLargestSum(A);
        System.out.println(result);
    }

    public static final int findLargestSum(int[] A){
        if(A==null || A.length==0)
            return 0;
        TreeMap<Integer,Integer> M = new TreeMap<>();
        for(int i : A){
            M.put(i,M.getOrDefault(i,0)+i);
        }
        System.out.println(M);
        int[][] DP = new int[M.size()][2];
        DP[0][1] = M.firstEntry().getValue();
        int counter=0, previous=M.firstKey();
        for(int K : M.keySet()){
            if(counter==0){
                counter++;
                continue;
            }
            DP[counter][0]=Math.max(DP[counter-1][0],DP[counter-1][1]);
            if(previous+1!=K){
                DP[counter][1]=Math.max(DP[counter-1][0],DP[counter-1][1])+M.get(K);
                previous=K;
            }else{
                DP[counter][1]=DP[counter-1][0]+M.get(K);
                previous++;
            }
            counter++;
        }
        return Math.max(DP[M.size()-1][0],DP[M.size()-1][1]);
    }

}

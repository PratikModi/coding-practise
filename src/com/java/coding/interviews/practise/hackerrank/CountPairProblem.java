package com.java.coding.interviews.practise.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Count all distinct pairs with difference equal to k
 * Difficulty Level : Medium
 * Last Updated : 18 Jun, 2021
 * Given an integer array and a positive integer k, count all distinct pairs with differences equal to k.
 *
 * Examples:
 *
 * Input: arr[] = {1, 5, 3, 4, 2}, k = 3
 * Output: 2
 * There are 2 pairs with difference 3, the pairs are {1, 4} and {5, 2}
 *
 * Input: arr[] = {8, 12, 16, 4, 0, 20}, k = 4
 * Output: 5
 * There are 5 pairs with difference 4, the pairs are {0, 4}, {4, 8},
 * {8, 12}, {12, 16} and {16, 20}
 */
public class CountPairProblem {

    public static void main(String[] args) {
        int[] A = {8, 12, 16, 4, 0, 20};
        int K = 4;
        System.out.println(pairs(A,K));
    }

    public static int pairs(int[] A, int K){
        if(A==null || A.length==0)
            return -1;
        int N = A.length;
        List<int[]> pairs = new ArrayList<>();
        Arrays.sort(A);
        int result=0;
        int R=1;
        int L=0;
        while(R<N){
            if(A[R]-A[L]==K){
                pairs.add(new int[]{A[L],A[R]});
                result++;
                R++;
                L++;
            }else if(A[R]-A[L]>K){
                L++;
            }else{
                R++;
            }
        }
        pairs.stream().forEach(r-> System.out.println(Arrays.toString(r)));
        return  result;
    }

}

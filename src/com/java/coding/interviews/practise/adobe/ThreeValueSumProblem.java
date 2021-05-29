package com.java.coding.interviews.practise.adobe;

import java.util.Arrays;

/**
 * Sum of Three Values
 * Problem Statement
 * Given an array of integers and a value, determine if there are any three integers in the array whose sum equals the given value.
 *
 * Consider this array and the target sums.
 *
 */
public class ThreeValueSumProblem {

    public static void main(String[] args) {
        int[] A = {12, 3, 4, 1, 6, 9};
        int target=24;
        System.out.println(find3Numbers(A,target));
    }

    public static boolean find3Numbers(int A[], int target){
        if(A==null || A.length==0)
            return false;

        Arrays.sort(A);
        for(int i=0;i<A.length-2;i++){
            int L = i+1;
            int R = A.length-1;
            while(L<R){
                int sum = A[i]+A[L]+A[R];
                if(sum==target){
                    System.out.println(A[i]+","+A[L]+","+A[R]);
                    return true;
                }else if(sum<target){
                    L++;
                }else{
                    R--;
                }
        }
        }
        return false;
    }

}

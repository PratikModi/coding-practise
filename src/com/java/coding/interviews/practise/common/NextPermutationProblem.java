package com.java.coding.interviews.practise.common;

import java.util.Arrays;
import java.util.Collections;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order
 * (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the
 * right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutationProblem {
    public static void main(String[] args) {
        int [] A = new int[] {799,556};
        nextPermutations(A);
        System.out.println(Arrays.toString(A));
    }

    public static void nextPermutations(int[] A){
        if(A==null || A.length==0)
            return;
        int i=0;
        int index=0;
        int N = A.length;
        for(i=N-1;i>0;i--){
            if(A[i]>A[i-1])
                break;
        }
        index=i;
        System.out.println(i);
        if(index==0) {
            Arrays.sort(A);
            return;
        }
        for(int j=i;j<N;j++){
            if(A[j]>A[i-1])
                index=j;
        }
        System.out.println(index);
        int temp = A[i-1];
        A[i-1]=A[index];
        A[index]=temp;
        Arrays.sort(A,i,N);
    }
}

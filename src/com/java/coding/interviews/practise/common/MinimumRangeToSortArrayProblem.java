package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.List;

/**
 * This problem was asked by WhatsApp.
 *
 * Given an array of integers out of order, determine the bounds of the smallest window
 * that must be sorted in order for the entire array to be sorted. For example,
 * given [3, 7, 5, 6, 9], you should return (1, 3).
 *
 * 1) Find the candidate unsorted sub array
 * a) Scan from left to right and find the first element which is greater than the next element.
 * Let s be the index of such an element. In the above example 1, s is 3 (index of 30).
 * b) Scan from right to left and find the first element (first in right to left order)
 * which is smaller than the next element (next in right to left order).
 * Let e be the index of such an element. In the above example 1, e is 7 (index of 31).
 *
 * 2) Check whether sorting the candidate unsorted sub array makes the complete array sorted or not.
 * If not, then include more elements in the sub array.
 * a) Find the minimum and maximum values in arr[s..e].
 * Let minimum and maximum values be min and max.
 * min and max for [30, 25, 40, 32, 31] are 25 and 40 respectively.
 * b) Find the first element (if there is any) in arr[0..s-1] which is greater than min,
 * change s to index of this element. There is no such element in above example 1.
 * c) Find the last element (if there is any) in arr[e+1..n-1] which is smaller than max,
 * change e to index of this element. In the above example 1, e is changed to 8 (index of 35)
 *
 * 3) Print s and e.
 */
public class MinimumRangeToSortArrayProblem {
    public static void main(String[] args) {
        int[] A = {3, 7, 5, 6, 9};
        System.out.println(findMinimumRange(A));
    }
    public static List<Integer> findMinimumRange(int[] A){
        List<Integer> result = new ArrayList<>();
        if(A==null || A.length==0)
            return result;
        int S=0,E=0,MAX=0,MIN=0;
        int N=A.length;
        for(S=0;S<N-1;S++){
            if(A[S]>A[S+1])
                break;
        }
        if(S==N-1){
            System.out.println("Array Already Sorted");
            return result;
        }
        for(E=N-1;E>0;E--){
            if(A[E]<A[E-1])
                break;
        }
        MAX=A[S];MIN=A[S];
        for(int i=S;i<E+1;i++){
            MAX=Math.max(MAX,A[i]);
            MIN=Math.min(MIN,A[i]);
        }

        for(int i=0;i<S;i++){
            if(A[i]>MIN){
                S=i;
                break;
            }
        }

        for(int i=N-1;i>E;i--){
            if(A[i]<MAX){
                E=i;
                break;
            }
        }
        result.add(S);
        result.add(E);
        return result;
    }


}

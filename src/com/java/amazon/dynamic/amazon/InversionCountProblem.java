package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

/**
 * Count Inversions in an array | Set 1 (Using Merge Sort)
 * Last Updated: 11-06-2020
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion
 * count is the maximum.
 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
 *
 * Input: arr[] = {8, 4, 2, 1}
 * Output: 6
 *
 * Explanation: Given array has six inversions:
 * (8,4), (4,2),(8,2), (8,1), (4,1), (2,1).
 *
 *
 * Input: arr[] = {3, 1, 2}
 * Output: 2
 *
 * Explanation: Given array has two inversions:
 * (3, 1), (3, 2)
 */

public class InversionCountProblem {

    public static void main(String[] args) {
        System.out.println(sortAndCount(new int[]{8,4,2,1},0,3));
        System.out.println(sortAndCount(new int[]{3,1,2},0,2));
    }

    public static int sortAndCount(int[] A, int low, int high){
        int count=0;
        if(low<high){
            int mid = (low+high)/2;
            count+=sortAndCount(A,low,mid);
            count+=sortAndCount(A,mid+1,high);
            count+=mergeAndCount(A,low,mid,high);
        }
        return count;
    }

    private static int mergeAndCount(int[] A, int low, int mid, int high){
        int L=0,R=0,T=low;
        int count=0;
        int[] left = Arrays.copyOfRange(A,low,mid+1);
        int[] right = Arrays.copyOfRange(A,mid+1,high+1);
        while(L<left.length && R<right.length){
            if(left[L] <= right[R]){
                A[T++] = left[L++];
            }else{
                A[T++] = right[R++];
                count+=(mid+1)-(L+low);
            }
        }
        while(L<left.length){
            A[T++]=left[L++];
        }
        while(R<right.length){
            A[T++]=right[R++];
        }
        return count;
    }

}

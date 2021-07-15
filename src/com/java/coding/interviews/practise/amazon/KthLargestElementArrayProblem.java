package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;

/**
 * Kth Largest Element in an Array
 * Medium
 *
 * 5775
 *
 * 368
 *
 * Add to List
 *
 * Share
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */

public class KthLargestElementArrayProblem {

    public static void main(String[] args) {
        int[] A = {3,2,3,1,2,4,5,5,6};
        int K = 4;
        System.out.println(findKthLargest(A,K));
    }

    public static int findKthLargest(int[] A, int K){
        int L=0;
        int N = A.length;
        int R = N-1;
        return findKthLargest(A,N-K,L,R);
    }

    private static int findKthLargest(int[] A, int K, int L, int R){
        if(L>R)
            return 0;
        int pIndex = partition(A,L,R);
        System.out.println(Arrays.toString(A));
        if(pIndex==K)
            return A[pIndex];
        else if(pIndex<K)
            return findKthLargest(A,K,pIndex+1,R);
        else
            return findKthLargest(A,K,L,pIndex-1);
    }

    private static int partition(int[] A, int L, int R){
        int pivot = A[R];
        int pIndex = L-1;
        for(int j=L;j<R;j++){
            if(A[j]<=pivot){
                pIndex++;
                int temp = A[j];
                A[j] = A[pIndex];
                A[pIndex]=temp;
            }
        }
        int temp = A[pIndex+1];
        A[pIndex+1] = A[R];
        A[R]=temp;
        return pIndex+1;
    }


}

package com.java.coding.interviews.practise.amazon;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */

public class RotateArrayProblem {

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5};
        int K=2;
        rotateArray(A,K);
        System.out.println(Arrays.toString(A));
        A =new int[] {1,2,3,4,5};
        rotateArrayUsingReverse(A,K);
        System.out.println(Arrays.toString(A));
    }

    public static void rotateArray(int[] A, int K){
        if(A==null || A.length==0)
            return;
        int N = A.length;
        K=K%N;
        int count=0;
        for(int start=0;count<N;start++){
            int current=start;
            int prev=A[current];
            do{
                int next = (current+K)%N;
                int temp = A[next];
                A[next] = prev;
                prev=temp;
                current=next;
                count++;
            }while(start!=current);
        }
    }

    public static void rotateArrayUsingReverse(int[] A , int K){
        if(A==null || A.length==0)
            return;
        int N = A.length;
        //K%=N;
        reverse(A,0,N-1);
        reverse(A,0,K-1);
        reverse(A,K,N-1);
    }

    private static void reverse(int[] A, int start, int end){
        while(start<end){
            int temp = A[start];
            A[start]=A[end];
            A[end]=temp;
            start++;
            end--;
        }
    }

}

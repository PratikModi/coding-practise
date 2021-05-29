package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 27-06-2020.
 */

import java.util.Arrays;

/**
 * Given a sorted array re-arrange them as following
 * [1,2,3,4,5] ==> [5,1,4,2,3]
 */
public class ArrayRearrangementProblem {
    public static void main(String[] args){
        int[] A = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(A));
        System.out.println("========================");
        //System.out.println(Arrays.toString(rearrangeArray(A)));
        System.out.println(Arrays.toString(rearrangeArrayOptimized(A)));
        System.out.println("+++++++++++++++++++++++++");
        A = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(A));
        System.out.println("========================");
        //System.out.println(Arrays.toString(rearrangeArray(A)));
        System.out.println(Arrays.toString(rearrangeArrayOptimized(A)));
    }
    //O(N) Space Complexity
    public static int[] rearrangeArray(int[] A){
        if(A==null || A.length==0)
            return A;
        int[] B = new int[A.length];
        int left=0;
        int right=A.length-1;
        int i=0;
        while(left<=right){
            B[i++]=A[right--];
            if(i<A.length)
                B[i++]=A[left++];
        }
        return B;
    }

    //O(1) Space Complexity
    public static int[] rearrangeArrayOptimized(int[] A) {
        if (A == null || A.length == 0)
            return A;
        int BASE_VALUE = A[A.length-1]+1;
        int left=0,right=A.length-1;
        int MAX_VALUE=A[right];
        int MIN_VALUE=A[left];
        for(int i=0;i<A.length;i++){
            if((i)%2==0){
                A[i]=A[i]+(MAX_VALUE%BASE_VALUE)*BASE_VALUE;
                MAX_VALUE=A[--right];
            }else{
                A[i]=A[i]+(MIN_VALUE%BASE_VALUE)*BASE_VALUE;
                MIN_VALUE=A[++left];
            }
        }
        //System.out.println(Arrays.toString(A));
        return Arrays.stream(A).map(e ->e/BASE_VALUE ).toArray();
    }
}

package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 24-05-2020.
 */

/**
 * Given an integer list where each number represents the number of hops you can make,
 * determine whether you can reach to the last index starting at index 0.
 * For example, [2, 0, 1, 0] returns True while [1, 1, 0, 1] returns False.
 */
public class ArrayJumpProblem {

    public static void main(String[] args) {
        System.out.println(canReachToEnd(new int[]{2,0,1,0}));
        System.out.println(canReachToEnd(new int[]{1,1,0,1}));
        System.out.println(canReachToEnd(new int[]{0}));

    }

    private static boolean canReachToEnd(int[] A){
        if(A==null || A.length==0)
            return false;
        int start = 0;
        while(start < A.length){
            if(A[start]==0) return false;
            int next= start + A[start];
            if(next >= A.length) return false;
            if(next == A.length-1)return true;
            start = next;
        }
        return false;
    }

}

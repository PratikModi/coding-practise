package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 28-06-2020.
 */

import java.util.Arrays;

/**
 * Given an array of non-negative integers, A, of length N, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Return the minimum number of jumps required to reach the last index.
 * If it is not possible to reach the last index, return -1.
 * Input 1:
 A = [2, 1, 1]

 Output 1:
 1

 Explanation 1:
 The shortest way to reach index 2 is
 Index 0 -> Index 2
 that requires only 1 jump.

 Input 2:
 A = [2,3,1,1,4]

 Output 2:
 2

 Explanation 2:
 The shortest way to reach index 4 is
 Index 0 -> Index 1 -> Index 4
 that requires 2 jumps.
 */
public class MinimumJumpProblem {
    public static void main(String[] args) {
        int[] A = {2,3,1,1,4};
        System.out.println(findMinimumJump(A));
    }

    public static int findMinimumJump(int[] A){
        if(A==null || A.length==0)
            return -1;
        if(A[0]==0)
            return -1;
        int[] jump = new int[A.length];
        int N = A.length;
        Arrays.fill(jump,Integer.MAX_VALUE-1);
        jump[0]=0;
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(i<=A[j]+j){
                    if(jump[i]>jump[j]+1){
                        jump[i]=jump[j]+1;
                    }
                }
            }
        }
        return jump[A.length-1];
    }

}

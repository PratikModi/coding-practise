package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 07-06-2020.
 */
public class ArraySmallerIndexProblem {
    public static void main(String[] args) {
        farthestSmallerIndex(new int[]{6,4,2,1},4);
    }

    public static void farthestSmallerIndex(int[] A, int N){
        if(A==null || A.length==0)
            return;
        int[] AR = new int[A.length];
        for(int i=0;i<N;i++){
            AR[i]=-1;
            for(int j=N-1;j>i;j--){
                //System.out.println(i+"==>"+j);
                if(A[i]>A[j]){
                    AR[i] = Math.max(AR[i],j);
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(AR));
    }
}

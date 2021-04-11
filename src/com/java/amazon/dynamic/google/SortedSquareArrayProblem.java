package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * Created by Pratik1 on 17-05-2020.
 */
public class SortedSquareArrayProblem {

    public static void main(String[] args) {
        int[] result=sortedSquareArray(new int[]{1,2,3});
        System.out.println(Arrays.toString(result));
        result=sortedSquareArray(new int[]{-3,-2,-1});
        System.out.println(Arrays.toString(result));

    }

    private static int[] sortedSquareArray(int[] A){
        if(A==null || A.length==0){
            return A;
        }
        int[] result = new int[A.length];
        int left=0;
        int right=A.length-1;
        for(int i=A.length-1;i>=0;i--){
            if(Math.abs(A[left])>Math.abs(A[right])){
                result[i] = A[left]*A[left];
                left++;
            }else{
                result[i] = A[right]*A[right];
                right--;
            }
        }
        return result;
    }

}

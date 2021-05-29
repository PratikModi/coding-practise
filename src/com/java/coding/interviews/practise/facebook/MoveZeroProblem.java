package com.java.coding.interviews.practise.facebook;

import java.util.Arrays;

/**
 * Arrays: move zeros to the left
 * Given an integer array, move all elements that are 0 to the left while maintaining the order of other elements in the array.
 * The array has to be modified in-place. Try it yourself before reviewing the solution and explanation.
 */

public class MoveZeroProblem {

    public static void main(String[] args) {
        int[] A = {1,10,20,0,59,63,0,88,0};
        moveZerosToLeft(A);
        System.out.println(Arrays.toString(A));
    }

    public static void moveZerosToLeft(int[] A){
        if(A==null || A.length==0){
            return;
        }
        int zeroCount=0;
        for(int i : A){
            if(i==0)
                zeroCount++;
        }
        int startIndex = A.length-1;
        for(int i=A.length-1;i>=0;i--){
            if(A[i]!=0 && startIndex>=zeroCount){
                A[startIndex--]=A[i];
            }
        }
        while(zeroCount>0){
            A[--zeroCount]=0;
        }
    }

}

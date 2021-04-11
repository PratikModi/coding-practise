package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * Equilibrium index of an array
 * Equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes.
 *  For example, in an array A:
 *
 * Example :
 *
 * Input: A[] = {-7, 1, 5, 2, -4, 3, 0}
 * Output: 3
 * 3 is an equilibrium index, because:
 * A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 *
 * Input: A[] = {1, 2, 3}
 * Output: -1
 */
public class EquilibriumArrayIndexProblem {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3};
        System.out.println(findEquilibriumPoint(A));
        A=new int[]{-7, 1, 5, 2, -4, 3, 0};
        System.out.println(findEquilibriumPoint(A));
    }

    public static int findEquilibriumPoint(int[] A){
        if(A==null || A.length==2)
            return -1;
        if(A.length==1)
            return 0;
        int N = A.length;
        int[] sumArray = new int[A.length];
        sumArray[0]=A[0];
        for(int i=1;i<N;i++){
            sumArray[i]=A[i]+sumArray[i-1];
        }
        System.out.println(Arrays.toString(sumArray));
        for(int i=1;i<N-1;i++){
            int leftSum = sumArray[i]-A[i];
            int rightSum = sumArray[N-1]-sumArray[i];
            if(leftSum==rightSum)
                return i;
        }
        return -1;
    }
}

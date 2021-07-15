package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 06-04-2020.
 */
public class ArrayMountainProblem {

    public static void main(String[] args) {
        System.out.println(isArrayFormMountain(new int[]{4,5,6,3,2,1}));
    }

    public static boolean isArrayFormMountain(int[] A){
        if(A==null || A.length==0)
            return false;
        int i=0;
        while(i<A.length && (i+1)<A.length && A[i]<A[i+1]){
            i++;
        }
        if(i==0 || (i)>=A.length){
            return false;
        }

        while(i<A.length && (i+1)<A.length){
            if(A[i] <= A[i++ +1])
                return false;
        }
        return true;
    }

}

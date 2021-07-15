package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class SortedArraySquareProblem {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedArraySquare(new int[]{1,2,3})));
        System.out.println(Arrays.toString(sortedArraySquare(new int[]{-3,-2,-1})));
    }

    public static int[] sortedArraySquare(int[] A){
        if(A==null || A.length==0)
            return A;
        int[] result = new int[A.length];
        int left=0;
        int right=A.length-1;
        for(int i=A.length-1;i>=0;i--){
            if(Math.abs(A[left])>Math.abs(A[right])){
                result[i]=(int)Math.pow(A[left],2);
                left++;
            }else{
                result[i]=(int)Math.pow(A[right],2);
                right--;
            }
        }
        return result;
    }

}

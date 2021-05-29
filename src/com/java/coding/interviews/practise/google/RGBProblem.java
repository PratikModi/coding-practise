package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array so that all the Rs come first,
 * the Gs come second, and the Bs come last. You can only swap elements of the array.
 *
 * Do this in linear time and in-place.
 *
 * For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
 */
public class RGBProblem {

    public static void main(String[] args) {
        char[] A = {'G', 'B', 'R', 'R', 'B', 'R', 'G'};
        arrangeCharacter(A);
        System.out.println(Arrays.toString(A));
    }

    public static void arrangeCharacter(char[] A){
        if(A==null || A.length==0)
            return;
        int low=0;
        int mid=0;
        int high=A.length-1;

        while(mid<=high){
            if(A[mid]=='R'){
                char temp = A[mid];
                A[mid]=A[low];
                A[low]=temp;
                low++;
                mid++;
            }else if(A[mid]=='G'){
                mid++;
            }else{
                char temp = A[mid];
                A[mid]=A[high];
                A[high]=temp;
                high--;
            }

        }

    }

}

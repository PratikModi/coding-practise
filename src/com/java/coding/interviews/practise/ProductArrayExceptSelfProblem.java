package com.java.coding.interviews.practise;

import java.util.Arrays;

/**
 * Created by Pratik1 on 26-04-2020.
 */
public class ProductArrayExceptSelfProblem {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productArray(new int[]{1,2,3,4})));
    }

    public static int[] productArray(int[] A){
        if(A==null || A.length==0)
            return A;
        int[] outputArray = new int[A.length];

        outputArray[0] = 1;
        for(int i=1;i<A.length;i++){
            outputArray[i]=A[i-1]*outputArray[i-1];
        }

        System.out.println(Arrays.toString(outputArray));

        int R=1;
        for(int i=A.length-1;i>=0;i--){
            outputArray[i]*=R;
            R*=A[i];
        }

        return outputArray;

    }


}

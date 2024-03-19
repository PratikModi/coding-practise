package com.java.coding.interviews.practise.coupang;

import java.util.Arrays;

public class ProductArrayExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productArray(new int[]{1,2,3,4})));
    }

    public static int[] productArray(int[] numArray){
        if(numArray==null || numArray.length==0)
            return numArray;
        int[] output = new int[numArray.length];
        output[0]=1;
        for(int i=1;i<numArray.length;i++){
            output[i]=output[i-1]*numArray[i-1];
        }
        System.out.println(Arrays.toString(output));
        int right=1;
        for(int i=numArray.length-1;i>=0;i--){
            output[i]*=right;
            right*=numArray[i];
        }
        return output;
    }
}

package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 15-02-2020.
 */
public class MaxProductSubArray {

    public static int findMaxProductSubArray(int[] array){
        int result =0;
        if(array==null || array.length==0)
            return result;
        int[] positiveProduct = new int[array.length];
        int[] negativeProduct = new int[array.length];
        int[] maxProduct = new int[array.length];

        positiveProduct[0] = negativeProduct[0] = maxProduct[0] = array[0];
        for(int i=1;i<array.length;i++){
            int a = positiveProduct[i-1] * array[i];
            int b = negativeProduct[i-1] * array[i];
            positiveProduct[i] = Math.max(Math.max(a,b),array[i]);
            negativeProduct[i] = Math.min(Math.min(a,b),array[i]);
            maxProduct[i] = Math.max(positiveProduct[i],maxProduct[i-1]);
        }
        System.out.println(Arrays.toString(positiveProduct));
        System.out.println(Arrays.toString(negativeProduct));
        result = maxProduct[array.length-1];
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findMaxProductSubArray(new int[]{-2,-3,-4,-3}));
        System.out.println(findMaxProductSubArray(new int[]{-2,0,-1}));
    }
}


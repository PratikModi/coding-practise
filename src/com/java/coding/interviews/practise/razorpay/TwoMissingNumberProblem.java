package com.java.coding.interviews.practise.razorpay;

import java.util.Arrays;

/**
 * Given an array containing all the numbers from 1 to n except two, find the two missing numbers.
 *
 * eg.
 *
 * missing([4, 2, 3]) = 1, 5
 *
 * Once you think that you’ve solved the problem, click below to see the solution.
 */
public class TwoMissingNumberProblem {
    public static void main(String[] args) {
        int[] A = {1,2,3};
        System.out.println(Arrays.toString(findMissingNumber(A)));
    }

    public static int[] findMissingNumber(int[] A){
        if(A==null || A.length==0)
            return new int[]{};
        int size = A.length+2;

        int totalSum = size*(size+1)/2;
        int actualSum=0;
        for(int i : A){
            actualSum+=i;
        }
        // totalSum - arrSum = the sum of the two results. Therefore we know
        // that since our two results are not equal, one result is
        // > (sum of two results) / 2 and the other is
        // < (sum of two results) / 2
        int pivot = (totalSum-actualSum)/2;
        System.out.println(pivot);
        // XOR the two values together. x^x = 0 and x^0 = x. That means that any
        // repeated number cancels out, so we are left with the single
        // non-repeated number.
        // eg. (1 ^ 2 ^ ... ^ N-1 ^ N) ^ (1 ^ 2 ^ ... ^ N-1) = N
        int totalLeftXor=0;
        int arrayLeftXor=0;
        int totalRightXor=0;
        int arrayRightXor=0;

        for(int i=1;i<=pivot;i++){
            totalLeftXor^=i;
        }
        for(int i =pivot+1;i<=size;i++){
            totalRightXor^=i;
        }
        for(int i : A){
            if(i<=pivot){
                arrayLeftXor^=i;
            }else{
                arrayRightXor^=i;
            }
        }
        System.out.println(totalLeftXor +"--"+arrayLeftXor);
        System.out.println(totalRightXor +"--"+arrayRightXor);

        return new int[]{totalLeftXor^arrayLeftXor,totalRightXor^arrayRightXor};
    }

}

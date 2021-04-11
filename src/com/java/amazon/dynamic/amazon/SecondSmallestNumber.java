package com.java.amazon.dynamic.amazon;

/**
 * Time Complexity O(n)
 */
public class SecondSmallestNumber {
    public static void main(String[] args) {
        int[] A = {1,3,5,9,2};
        System.out.println(secondSmallest(A));
    }

    private static int secondSmallest(int[] A){
        int smallest=Integer.MAX_VALUE;
        if(A==null || A.length<2)
            return -1;
        int secondSmallest=Integer.MAX_VALUE;

        for(int i=0;i<A.length;i++){
            int element=A[i];
            if(element<smallest){
                secondSmallest=smallest;
                smallest=element;
            }else if(element<secondSmallest){
                secondSmallest=element;
            }
        }
        return secondSmallest;
    }
}

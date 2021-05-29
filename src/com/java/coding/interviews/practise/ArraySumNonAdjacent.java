package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class ArraySumNonAdjacent {

    public static void main(String[] args) {
        System.out.println(findNonAdjacent(new int[]{4,1,4,1,4}));
        System.out.println(findNonAdjacent(new int[]{4,2,2,4}));
    }

    public static int findNonAdjacent(int[] A){
        if(A==null || A.length==0)
            return 0;
        int include = A[0];
        int exclude = 0;
        for(int i=1;i<A.length;i++){
            int temp = include;
            include = Math.max(exclude+A[i],include);
            exclude = temp;
        }
        return include;
    }
}

package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 07-06-2020.
 */
public class ArrayEqualizationProblem {

    public static void main(String[] args) {
        System.out.println(equalizeArrayCost(new int[]{1,6,7,10}));
    }

    public static int equalizeArrayCost(int[] A){
        int result=0;
        if(A==null | A.length==0)
            return result;
        int N=A.length;
        int mid=A[N/2];
        int cost=0;
        for(int i=0;i<N;i++){
            cost+=Math.abs(A[i]-mid);
        }
        result=cost;
        if(N%2==0){
            int tempCost=0;
            mid=A[N/2-1];
            for(int i=0;i<N;i++){
                tempCost+=Math.abs(A[i]-mid);
            }
            result=Math.min(cost,tempCost);
        }
        return result;
    }

}

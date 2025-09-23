package com.java.coding.interviews.practise.microsoft;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumKProblem {

    public static void main(String[] args) {
        System.out.println(countSubArray(new int[]{10,2,-2,-10,-20,10},-10));
        System.out.println(countSubArray(new int[]{1,2,1,2,1},3));
    }

    public static int countSubArray(int[] A, int S){
        if(A==null || A.length==0)
            return -1;
        Map<Integer, Integer> previous_sum = new HashMap<>();
        int N = A.length;
        int result=0;
        int current_sum=0;
        for(int i=0;i<N;i++){
            current_sum+=A[i];
            if(current_sum==S)
                result++;
            if(previous_sum.containsKey(current_sum-S)){
                result+=previous_sum.get(current_sum-S);
            }
            previous_sum.putIfAbsent(current_sum,0);
            previous_sum.put(current_sum,previous_sum.get(current_sum)+1);
            System.out.println(previous_sum);
        }
        return result;
    }
}

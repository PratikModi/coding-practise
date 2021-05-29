package com.java.coding.interviews.practise;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Pratik1 on 27-04-2020.
 */
public class SubArraySumKProblem {

    public static void main(String[] args) {
        System.out.println(countSubArray(new int[]{10,2,-2,-10,-20,10},-10));
    }



    public static int countSubArray(int[] A, int K){

        int result=0;
        if(A==null || A.length==0)
            return result;
        int current_sum=0;
        Map<Integer,Integer> previous_sum = new LinkedHashMap<>();
        for(int i=0;i<A.length;i++){
            current_sum+=A[i];

            if(current_sum==K)
                result++;
            // To find the array with single element
            if(previous_sum.containsKey(current_sum-K)){
                result+=previous_sum.get(current_sum-K);
            }
            previous_sum.put(current_sum,previous_sum.getOrDefault(current_sum,0)+1);
            System.out.println(result);
        }
        System.out.println(previous_sum);
        return result;
    }

}

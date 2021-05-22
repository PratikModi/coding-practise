package com.java.amazon.dynamic.twilio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an Array , E.g. [2,5,6,3,9,5,10,56,25] and a number X.
 * now find all the contigous subarrays with length X , if X=3,
 * then all the subarrays are [2,5,6], [5,6,3], [6,3,9] etc.
 * now in all these subarrays find the minimum element. and out of all those minimum elements, return the maximum one.
 */
public class SubArraySumProblem {
    public static void main(String[] args) {
        int[] A = {2,5,6,3,9,4,5,10,56,25};
        System.out.println(findSubArray(A,13));
        System.out.println(contiguousSubArray(A,3));
    }

    public static List<List<Integer>> findSubArray(int[] A, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(A==null || A.length==0 || target==0)
            return result;
        int start=0,N=A.length;
        int sum=0;
        for(int i=0;i<N;i++){
            while(sum>target && start < i){
                sum-=A[start++];
            }
            if(sum==target){
                List<Integer> subArray = new ArrayList<>();
                for(int j=start;j<i;j++){
                    subArray.add(A[j]);
                }
                result.add(subArray);
            }
            sum+=A[i];
        }
        return result;
    }


    public static int contiguousSubArray(int[] A, int X){
        int result =Integer.MAX_VALUE;
        List<List<Integer>> L = new ArrayList<>();
        if(A==null || A.length==0 || X==0)
            return result;
        int N = A.length;
        for(int i=0;i<N-X;i++){
            List<Integer> subArray = new ArrayList<>();
            for(int j=i;j<i+X;j++){
                subArray.add(A[j]);
            }
            Collections.sort(subArray);
            L.add(subArray);
        }
        System.out.println(L);
        for(List<Integer> T : L){
            result = Math.min(result,T.get(0));
        }

        return result;
    }
}

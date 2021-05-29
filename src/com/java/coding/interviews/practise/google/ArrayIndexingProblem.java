package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 11-06-2020.
 */

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * This problem was asked by Google.
 Given an array of numbers and an index i, return the index of the
 nearest larger number of the number at index i, where distance is measured in array indices.

 For example, given [4, 1, 3, 5, 6] and index 0, you should return 3.

 If two distances to larger numbers are the equal, then return any one of them.
 If the array at i doesn't have a nearest larger integer, then return null.
 */
public class ArrayIndexingProblem {
    public static void main(String[] args) {
        int[] result = getLargerNumberIndex(new int[]{4,1,3,5,6});
        System.out.println(Arrays.toString(result));
        result = getNextGreaterElement(new int[]{4,1,3,5,6});
        System.out.println(Arrays.toString(result));
    }

    public static int[] getLargerNumberIndex(int[] A){
        if(A==null || A.length==0)
            return A;
        int[] output = new int[A.length];
        Arrays.fill(output,-1);
        int N = A.length;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(A[i]<=A[j]) {
                    output[i] = j;
                    break;
                }
            }
        }
        return output;
    }

    public static int[] getNextGreaterElement(int[] A){
        if(A==null || A.length==0)
            return A;
        int[] output = new int[A.length];
        Arrays.fill(output,-1);
        int N=A.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i=1;i<N;i++){
            if(A[i]>A[stack.peek()]){
                while(!stack.isEmpty() && A[i]>A[stack.peek()]){
                    int index = stack.pop();
                    output[index]=i;
                }
            }
            stack.push(i);
        }
        //Arrays.asList(output).stream().collect(Collectors.toList());
        return output;
    }
}

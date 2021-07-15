package com.java.coding.interviews.practise.facebook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Minimizing Permutations
 *
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N).
 * You want to rearrange the elements of the permutation into increasing order, repeatedly making the following operation:
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 *
 * Signature
 *
 * int minOperations(int[] arr)
 * Input
 * Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
 *
 * Output
 * An integer denoting the minimum number of operations required to arrange the permutation in increasing order
 * Example
 * If N = 3, and P = (3, 1, 2), we can do the following operations:
 * Select (1, 2) and reverse it: P = (3, 2, 1).
 * Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 * output = 2
 */

public class MinimizingPermutationProblem {

    public static void main(String[] args) {
        int[] A = {3,1,2};
        System.out.println(minOperations(A));
    }

    public static int minOperations(int[] A){
        Map<Integer,Integer> indexMap = new HashMap<>();
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Comparator.naturalOrder());
        for(int i=0;i<A.length;i++){
            PQ.offer(A[i]);
            indexMap.put(A[i],i);
        }
        int current=0;
        int swapCount=0;
        while(!PQ.isEmpty()){
            int N = PQ.poll();
            if(N<A[current]){
                reverse(A,Math.min(indexMap.get(N),current), Math.max(indexMap.get(N),current),indexMap);
                swapCount++;
            }
            current++;
        }
        return swapCount;
    }

    private static void reverse(int[] A, int min, int max, Map<Integer,Integer> indexMap){
        for(int i=min,j=max;i<=max && j>i;i++,j--){
            swap(A,i,j);
            indexMap.put(A[i],j);
            indexMap.put(A[j],i);
        }
    }

    private static void swap(int[] A, int i , int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}

package com.java.amazon.dynamic.google;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given two integers N and K, find the Kth permutation sequence of numbers from 1 to N without using STL function.
 *
 * Note: Assume that the inputs are such that Kth permutation of N number is always possible.
 *
 * Examples:
 *
 * Input: N = 3, K = 4
 * Output: 231
 * Explanation:
 * The ordered list of permutation sequence from integer 1 to 3 is : 123, 132, 213, 231, 312, 321.
 * So, the 4th permutation sequence is “231”.
 *
 * Input: N = 2, K = 1
 * Output: 12
 * Explanation:
 * For n = 2, only 2 permutations are possible 12 21. So, the 1st permutation sequence is “12”.
 */
public class KthNumberPermutationProblem {
    public static void main(String[] args) {
        System.out.println(findKthPermutation(3,4));
        List<Integer> L = Arrays.asList(1,2,3,4,5);
        System.out.println(L.stream().map(i->String.valueOf(i)).collect(Collectors.joining()));

    }
    public static String findKthPermutation(int N, int K){
        if(N==0){
            return "";
        }
        int[] fact = new int[N+1];
        fact[0]=1;
        for(int i=1;i<=N;i++){
            fact[i]=fact[i-1]*i;
        }
        //System.out.println(Arrays.toString(fact));
        List<Integer> nums = IntStream.range(1,N+1).mapToObj(x->x).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        K--;
        for(int i=1;i<=N;i++){
            int index = K/fact[N-i];
            sb.append(nums.get(index));
            nums.remove(nums.get(index));
            K-=index*fact[N-i];
        }
        return sb.toString();
    }
}

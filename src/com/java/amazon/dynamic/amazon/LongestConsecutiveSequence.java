package com.java.amazon.dynamic.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Longest Consecutive Sequence
 * Asked in:
 * Amazon
 * Google
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Example:
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        List<Integer> L = Arrays.asList(100, 4, 200, 1, 3, 2);
        int result = findLongestConsecutiveSequence(L);
        System.out.println(result);
    }

    public static int findLongestConsecutiveSequence(final List<Integer> A){
        int result=0;
        if(A==null || A.isEmpty())
            return result;
        Map<Integer,Integer> M = new HashMap<>();
        for(int i : A){
            M.put(i,0);
        }
        for(int i : A){
            if(!M.containsKey(i)) continue;
            int n=i;
            int n1=i+1;
            int n2=i-1;
            while(M.containsKey(n1)){
                M.remove(n1);
                n1++;
            }
            while(M.containsKey(n2)){
                M.remove(n2);
                n2--;
            }
            result=Math.max(result,n1-n2-1);
        }
        return result;
    }
}

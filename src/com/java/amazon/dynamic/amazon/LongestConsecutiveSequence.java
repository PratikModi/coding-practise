package com.java.amazon.dynamic.amazon;

import java.util.*;

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
        result = findLongestConsecutiveSequenceUsingPQ(L);
        System.out.println(result);
    }

    public static int findLongestConsecutiveSequence(final List<Integer> A){
        int result=0;
        if(A==null || A.isEmpty())
            return result;
        Set<Integer> M = new HashSet<>();
        for(int i : A){
            M.add(i);
        }
        System.out.println(M);
        for(int i : A){
            if(!M.contains(i)) continue;
            int n=i;
            int n1=i+1;
            int n2=i-1;
            while(M.contains(n1)){
                M.remove(n1);
                n1++;
            }
            while(M.contains(n2)){
                M.remove(n2);
                n2--;
            }
            result=Math.max(result,n1-n2-1);
        }
        return result;
    }
    //1,2,3,4,100,200
    public static int findLongestConsecutiveSequenceUsingPQ(final List<Integer> A){
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        int max=1;
        int counter=1;
        for(int I : A){
            PQ.add(I);
        }
        int N = A.size();
        int previous = PQ.poll();
        for(int i=1;i<N;i++){
            if(PQ.peek()-previous>1){
                counter=1;
                previous=PQ.poll();
            }else if(PQ.peek()-previous==0){
                previous=PQ.poll();
            }else{
                counter++;
                previous=PQ.poll();
            }
            max=Math.max(max,counter);
        }
        return max;
    }

}

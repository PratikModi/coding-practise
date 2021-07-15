package com.java.coding.interviews.practise.walmart;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * K’th largest element in a stream
 * Difficulty Level : Medium
 * Last Updated : 08 Jun, 2021
 * Given an infinite stream of integers, find the k’th largest element at any point of time.
 * Example:
 *
 * Input:
 * stream[] = {10, 20, 11, 70, 50, 40, 100, 5, ...}
 * k = 3
 *
 * Output:    {_,   _, 10, 11, 20, 40, 50,  50, ...}
 */
public class KthLargestElementInStreamProblem {

    public static void main(String[] args) {
        int A[] = {10, 20, 11, 70, 50, 40, 100, 5};
        System.out.println(findKthLargestElement(A,3));
    }

    public static List<Integer> findKthLargestElement(int[] A, int K){
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        List<Integer> L = new ArrayList<>();

        for(int i: A){
            if(PQ.size()<K){
                PQ.offer(i);
            }else if(i>PQ.peek()){
                PQ.poll();
                PQ.offer(i);
            }
            if(PQ.size()>=K){
                L.add(PQ.poll());
            }else{
                L.add(-1);
            }
        }
        return L;
    }

}

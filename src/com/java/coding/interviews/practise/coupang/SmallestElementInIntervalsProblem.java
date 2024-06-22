package com.java.coding.interviews.practise.coupang;

import java.util.PriorityQueue;

/**
 * Given an array of intervals arr[] of size N, the task is to find the Kth smallest element among all the elements within the intervals of the given array.
 *
 * Examples:
 *
 * Input : arr[] = {{5, 11}, {10, 15}, {12, 20}}, K =12
 * Output: 13
 * Explanation: Elements in the given array of intervals are: {5, 6, 7, 8, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 17, 18, 19, 20}.
 * Therefore, the Kth(=12th) smallest element is 13.
 *
 * Input: arr[] = {{5, 11}, {10, 15}, {12, 20}}, K = 7
 * Output:10
 */

//Time Complexity(XLog(X)) X-- Total number of elements in intervals
//Space Complexity(XLog(X))
public class SmallestElementInIntervalsProblem {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{5, 11}, {10, 15}, {12, 20}};
        int k =12;
        int smallest = kThSmallestElement(intervals,k);
        System.out.println(smallest);
    }

    public static int kThSmallestElement(int[][] intervals, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int i=0;i<intervals.length;i++){
            pq.add(new int[]{intervals[i][0],intervals[i][1]});
        }
        int count=1;
        while(count<k){
            int[] interval = pq.poll();
            System.out.println(interval[0]+"--"+interval[1]);
            if(interval[0]<interval[1]){
                pq.add(new int[]{interval[0]+1,interval[1]});
            }
            count++;
        }
        return pq.peek()[0];
    }

}



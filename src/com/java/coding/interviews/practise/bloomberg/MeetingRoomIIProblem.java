package com.java.coding.interviews.practise.bloomberg;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
 */

public class MeetingRoomIIProblem {


    public static void main(String[] args) {
        int[][] intervals = {{2,15},{36,45},{9,29},{16,23},{4,9}};
        System.out.println(findMinimumRooms(intervals));
    }

    public static int findMinimumRooms(int[][] intervals){
        if(intervals==null || intervals.length==0)
            return 0;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        minHeap.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] current = intervals[i];
            int[] earliestEnding = minHeap.remove();
            if(current[0]>=earliestEnding[1]){
                earliestEnding[1] = Math.max(current[1],earliestEnding[1]);
            }else{
                minHeap.add(current);
            }
            minHeap.add(earliestEnding);
        }
        return minHeap.size();
    }

}




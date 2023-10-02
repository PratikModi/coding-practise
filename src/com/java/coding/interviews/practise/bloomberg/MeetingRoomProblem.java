package com.java.coding.interviews.practise.bloomberg;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if a person could attend all meetings.
 *
 * For example,
 * Given [ [0, 30], [5, 10], [15, 20] ],
 * return false.
 */

public class MeetingRoomProblem {

    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(checkIfAllMeetingsCanBeAttended(intervals));
        int[][] intervals_2 = {{0,15},{15,30},{30,45}};
        System.out.println(checkIfAllMeetingsCanBeAttended(intervals_2));
    }

    public static boolean checkIfAllMeetingsCanBeAttended(int[][] intervals){
        if(intervals==null || intervals.length==0)
            return false;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        minHeap.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] current = intervals[i];
            int[] earliestEnding = minHeap.remove();
            if(current[0]<earliestEnding[1])
                return false;
            else{
                earliestEnding[1] = current[1];
            }
            minHeap.add(earliestEnding);
        }
        System.out.println(minHeap.size());
        return true;
    }

}

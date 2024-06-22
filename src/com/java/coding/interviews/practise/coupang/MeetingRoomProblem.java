package com.java.coding.interviews.practise.coupang;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomProblem {

    public static void main(String[] args) {
        int[][] intervals = {{2,15},{36,45},{9,29},{16,23},{4,9}};
        System.out.println(findTotalMeetingRoomsRequired(intervals));
        intervals = new int[][]{{1,2},{3,4}};
        System.out.println(findTotalMeetingRoomsRequired(intervals));
    }

    public static int findTotalMeetingRoomsRequired(int[][] meetings){
        if(meetings==null || meetings.length==0)
            return 0;
        Arrays.sort(meetings,(a,b)->a[0]-b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.add(meetings[0]);
        for(int i=1;i<meetings.length;i++){
            int[] current = meetings[i];
            int[] earliestEnding = pq.remove();
            if(current[0]>=earliestEnding[1]){
                earliestEnding[1] = Math.max(current[1],earliestEnding[1]);
            }else{
                pq.add(current);
            }
            pq.add(earliestEnding);
        }
        return pq.size();
    }

}

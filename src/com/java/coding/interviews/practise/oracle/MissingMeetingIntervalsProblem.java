package com.java.coding.interviews.practise.oracle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Given multiple meetings which happen within a day. Return all the time brackets of no meetings.
 * Input: [(2, 6), (2, 3) (8, 10), (15, 18)]
 * Output: [(1, 2), (6, 8), (10, 15), (18, 24)]
 */
public class MissingMeetingIntervalsProblem {

    public static void main(String[] args) {
        int[][] meetings = {{2,6},{2,3},{8,10},{15,18}};
        var result = missingMeetingIntervals(meetings);
        result.stream().forEach(e-> System.out.println(Arrays.toString(e)));
    }

    public static List<int[]> missingMeetingIntervals(int[][] meetings){
        List<int[]> missingIntervals = new ArrayList<>();
        int[] previous = new int[]{1,1};
        int[] last = new int[]{24,24};
        Arrays.sort(meetings,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        for(var meeting : meetings){
            if(previous[1]<meeting[0]){
                missingIntervals.add(new int[]{previous[1],meeting[0]});
            }
            previous=meeting;
        }
        if(previous[1]<last[0]){
            missingIntervals.add(new int[]{previous[1],last[0]});
        }
        return missingIntervals;
    }

}

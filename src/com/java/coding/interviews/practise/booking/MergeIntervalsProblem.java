package com.java.coding.interviews.practise.booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Given intervals [1,3],[6,9] insert and merge [2,5] would result in [1,5],[6,9].
 *
 * Example 2:
 *
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would result in [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * Make sure the returned intervals are also sorted.
 */

public class MergeIntervalsProblem {

    public static List<int[]> mergeIntervals(List<int[]> intervals, int[] newInterval){
        List<int[]> mergedIntervals = new ArrayList<>();
        if(intervals==null || intervals.isEmpty() || newInterval==null || newInterval.length==0)
            return mergedIntervals;
        int i=0;
        int N=intervals.size();
        while(i<N && intervals.get(i)[1]<newInterval[0]){
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        while(i<=N && intervals.get(i)[0]<newInterval[1]){
            newStart = Math.min(newStart,intervals.get(i)[0]);
            newEnd = Math.max(newEnd,intervals.get(i)[1]);
            i++;
        }
        mergedIntervals.add(new int[]{newStart,newEnd});
        while(i<N){
            mergedIntervals.add(intervals.get(i++));
        }
        return mergedIntervals;
    }

    public static void main(String[] args) {
        //[1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9]
        List<int[]> intervals = List.of(
                new int[]{1,2},
                new int[]{3,5},
                new int[]{6,7},
                new int[]{8,10},
                new int[]{12,16}
                );
        int[] newInterval = new int[]{4,9};
        var mergedIntervals = mergeIntervals(intervals,newInterval);
        mergedIntervals.stream().forEach(e-> System.out.println(Arrays.toString(e)));
    }


}

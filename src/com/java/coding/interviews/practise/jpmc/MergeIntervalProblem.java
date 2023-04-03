package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example:
 *
 * Given [1,3],[2,6],[8,10],[15,18],
 *
 * return [1,6],[8,10],[15,18].
 *
 * Make sure the returned intervals are sorted.
 */
public class MergeIntervalProblem {


    private static List<int[]> mergeIntervals(List<int[]> intervals){
        List<int[]> mergedIntervals = new ArrayList<>();
        if(intervals==null || intervals.size()<2){
            return intervals;
        }
        int[] currentInterval = intervals.get(0);
        mergedIntervals.add(currentInterval);
        for(int i=1;i<intervals.size();i++){
            if(currentInterval[1]>intervals.get(i)[0]){
                currentInterval[1] = Math.max(currentInterval[1],intervals.get(i)[1]);
            }else{
                currentInterval = intervals.get(i);
                mergedIntervals.add(currentInterval);
            }
        }
        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{1,3});
        intervals.add(new int[]{2,6});
        intervals.add(new int[]{8,10});
        intervals.add(new int[]{15,18});
        var mergedIntervals = mergeIntervals(intervals);
        mergedIntervals.stream().forEach(e-> System.out.println(Arrays.toString(e)));
    }

}

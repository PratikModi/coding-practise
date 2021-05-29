package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
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

public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,4));
        intervals.add(new Interval(5,6));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));
        Interval newInterval = new Interval(7,11);
        List<Interval> result = mergeInterval(intervals,newInterval);
        System.out.println(result);
    }
    public static List<Interval> mergeInterval(List<Interval> intervals, Interval newInterval){
        List<Interval> result = new ArrayList<>();
        if(intervals==null || intervals.isEmpty()){
            result.add(newInterval);
            return result;
        }
        int i=0;
        int N=intervals.size();
        while(i<N && intervals.get(i).end < newInterval.start){
            result.add(intervals.get(i++));
        }
        int newStart=newInterval.start;
        int newEnd=newInterval.end;
        while(i<=N && intervals.get(i).start<=newInterval.end){
            newStart = Math.min(intervals.get(i).start,newStart);
            newEnd = Math.max(intervals.get(i).end,newEnd);
            i++;
        }
        result.add(new Interval(newStart,newEnd));
        while(i<N){
            result.add(intervals.get(i++));
        }
        return result;
    }

}
class Interval{
    int start;
    int end;
    Interval(int start,int end){
        this.start=start;
        this.end=end;
    }
    public String toString(){
        return "["+this.start+","+this.end+"]";
    }
}

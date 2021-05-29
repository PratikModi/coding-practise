package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
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
public class MergeOverlappingInterval {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        Interval i1 = new Interval(1,3);
        Interval i2 = new Interval(2,6);
        Interval i3 = new Interval(8,10);
        Interval i4 = new Interval(15,18);
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        System.out.println(mergeOverlappingInterval(intervals));
    }

    public static List<Interval> mergeOverlappingInterval(List<Interval> intervals){
        List<Interval>  result = new ArrayList<>();
        if(intervals==null || intervals.size()<2)
            return intervals;
        Interval current = intervals.get(0);
        result.add(current);
        for(int i=1;i<intervals.size();i++){
            if(current.end >=intervals.get(i).start){
                current.end=Math.max(current.end,intervals.get(i).end);
            }else{
                current=intervals.get(i);
                result.add(current);
            }
        }
        return result;
    }


}

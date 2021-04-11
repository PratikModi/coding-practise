package com.java.amazon.dynamic.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Employee Free Time
 * Hard
 *
 * 693
 *
 * 50
 *
 * Add to List
 *
 * Share
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 *
 * Constraints:
 *
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTimeProblem {
    public static void main(String[] args) {
        List<List<Interval>> intervals = new ArrayList<>();
        List<Interval> emp1 = new ArrayList<>();
        emp1.add(new Interval(1,3));
        emp1.add(new Interval(6,7));
        List<Interval> emp2 = new ArrayList<>();
        emp2.add(new Interval(2,4));
        List<Interval> emp3 = new ArrayList<>();
        emp3.add(new Interval(2,5));
        emp3.add(new Interval(9,12));
        intervals.add(emp1);
        intervals.add(emp2);
        intervals.add(emp3);
        List<Interval> result = employeeFreeTime(intervals);
        System.out.println(result);
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> intervals){
        if(intervals==null || intervals.isEmpty())
            return null;
        List<Interval> flatIntervals = new ArrayList<>();
        for(List<Interval> interval : intervals){
            flatIntervals.addAll(interval);
        }
        List<Interval> result = new ArrayList<>();
        List<Interval> mergedIntervals=merge(flatIntervals);
        for(int i=1;i<mergedIntervals.size();i++){
            Interval previous = mergedIntervals.get(i-1);
            Interval current = mergedIntervals.get(i);
            if(previous.end<current.start){
                result.add(new Interval(previous.end,current.start));
            }
        }
        return result;
    }


    private static List<Interval> merge(List<Interval> intervals){
        if(intervals==null) return null;
        if(intervals.size()<=1) return intervals;
        List<Interval> mergedIntervals = new ArrayList<>();
        Collections.sort(intervals,(i1,i2)->i1.start-i2.start);
        Interval previous = intervals.get(0);
        mergedIntervals.add(previous);
        for(int i=1;i<intervals.size();i++){
            Interval current = intervals.get(i);
            if(previous.end >= current.start){
                previous.end=Math.max(previous.end,current.end);
            }else{
                previous=current;//intervals.get(i);
                mergedIntervals.add(current);
            }
        }
        return mergedIntervals;
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

package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 17-05-2020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of closed intervals, find the smallest set of numbers that covers all the intervals.
 * If there are multiple smallest sets, return any of them.
 For example, given the intervals [0, 3], [2, 6], [3, 4], [6, 9], one set of numbers that covers all
 these intervals is {3, 6}.
 */
public class IntegerIntervalProblem {

    public static void main(String[] args) {
        int[][] intervals = {{0,3},{2,6},{3,4},{6,9}};
        System.out.println(findIntervals(intervals));
    }

    public static List<Integer> findIntervals(int[][] intervals){
        List<Integer> result = new ArrayList<>();
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        result.add(intervals[0][1]);
        Arrays.sort(intervals,(a,b)->b[0]-a[0]);
        result.add(intervals[0][0]);
        return result;
    }

}

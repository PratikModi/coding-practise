package com.java.coding.interviews.practise.coupang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeIntervalProblem {

    public static void main(String[] args) {
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{1,3});
        intervals.add(new int[]{2,6});
        intervals.add(new int[]{8,10});
        intervals.add(new int[]{15,18});
        var mergedIntervals = mergeIntervals(intervals);
        mergedIntervals.stream().forEach(e-> System.out.println(Arrays.toString(e)));
    }

    public static List<int[]> mergeIntervals(List<int[]> intervals){
        List<int[]> mergedIntervals = new ArrayList<>();
        if(intervals==null || intervals.size()<2)
            return mergedIntervals;
        Collections.sort(intervals,(a,b)->a[0]-b[0]);
        int[] last = intervals.get(0);
        mergedIntervals.add(last);
        for(int i=1;i<intervals.size();i++){
            int[] current = intervals.get(i);
            if(last[1]>current[0]){
                last[1] = Math.max(last[1],current[1]);
            }else{
                last = current;
                mergedIntervals.add(last);
            }
        }
        return mergedIntervals;
    }

}

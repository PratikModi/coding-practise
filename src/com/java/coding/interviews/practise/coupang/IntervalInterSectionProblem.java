package com.java.coding.interviews.practise.coupang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 986. Interval List Intersections
 * Medium
 * Topics
 * Companies
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 */
public class IntervalInterSectionProblem {

    public static void main(String[] args) {
        int[][] first=new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] second =new int[][]{{1,5},{8,12},{15,24},{25,26}};
        var result = findIntervalInterSection(first,second);
        Arrays.stream(result).forEach(e-> System.out.println(Arrays.toString(e)));
    }

    public static int[][] findIntervalInterSection(int[][] first, int[][] second){
        if(first.length==0 || second.length==0) return new int[0][0];
        int i=0;
        int j=0;
        int startMax=0, endMin=0;
        List<int[]> result = new ArrayList<>();
        while(i<first.length && j<second.length){
            startMax = Math.max(first[i][0],second[j][0]);
            endMin = Math.min(first[i][1],second[j][1]);

            if(endMin>=startMax){
                result.add(new int[]{startMax,endMin});
            }

            if(endMin==first[i][1])i++;
            if(endMin==second[j][1])j++;
        }
        return result.toArray(new int[result.size()][2]);
    }

}

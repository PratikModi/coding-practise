package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * Example 2:
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 */

public class JobSchedulingProblem {

    public static void main(String[] args) {
        System.out.println(scheduleJobs(new int[]{1,2,3,4,6},new int[]{3,5,10,6,9},new int[]{20,20,100,70,60}));
    }

    private static int scheduleJobs(int[] startTime, int[] endTime, int[] profit){
        List<Job> jobs = new ArrayList<>();
        if(startTime.length!=endTime.length){
            return 0;
        }
        if(endTime.length!=profit.length){
            return 0;
        }

        for(int i=0;i<startTime.length;i++){
            jobs.add(new Job(startTime[i],endTime[i],profit[i]));
        }
        Collections.sort(jobs,(a,b)->a.endTime-b.endTime);
        int result=0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(0,0);
        for(Job job : jobs){
            int lastEntryTillStartTime = map.floorKey(job.startTime);
            int maxProfitTillStartTime = map.get(lastEntryTillStartTime);
            result = Math.max(result,maxProfitTillStartTime+job.profit);
            map.put(job.endTime,result);
        }
        return result;
    }

}
class Job{

    int startTime;
    int endTime;
    int profit;

    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Job{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", profit=" + profit +
                '}';
    }
}

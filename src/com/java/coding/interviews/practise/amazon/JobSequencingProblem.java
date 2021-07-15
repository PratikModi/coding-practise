package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * Job Sequencing Problem
 * Difficulty Level : Medium
 * Last Updated : 03 May, 2021
 *
 * Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline.
 * It is also given that every job takes a single unit of time, so the minimum possible deadline for any job is 1.
 * How to maximize total profit if only one job can be scheduled at a time.
 *
 * Examples:
 *
 * Input: Four Jobs with following
 * deadlines and profits
 * JobID  Deadline  Profit
 *   a      4        20
 *   b      1        10
 *   c      1        40
 *   d      1        30
 * Output: Following is maximum
 * profit sequence of jobs
 *         c, a
 *
 *
 * Input:  Five Jobs with following
 * deadlines and profits
 * JobID   Deadline  Profit
 *   a       2        100
 *   b       1        19
 *   c       2        27
 *   d       1        25
 *   e       3        15
 * Output: Following is maximum
 * profit sequence of jobs
 *         c, a, e
 */
public class JobSequencingProblem {

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job('a',2,100));
        jobs.add(new Job('b',1,19));
        jobs.add(new Job('c',2,27));
        jobs.add(new Job('d',1,25));
        jobs.add(new Job('e',3,15));
        System.out.println(Arrays.toString(sequenceTheJobs(jobs,3)));
    }

    public static Job[] sequenceTheJobs(List<Job> jobs, int T){
        if(jobs==null || T==0)
            return null;
        Job[] result = new Job[T];
        boolean[] slots = new boolean[T];
        Collections.sort(jobs,((j1, j2) -> j2.profit-j1.profit));

        for(int i=0;i<jobs.size();i++){
            for(int j= Math.min(T-1,jobs.get(i).deadline-1);j>=0;j--){
                if(!slots[j]){
                    slots[j]=true;
                    result[j]= jobs.get(i);
                    break;
                }
            }
        }
        return result;
    }

}
class Job{
    char c;
    int deadline;
    int profit;

    public Job(char c, int deadline, int profit) {
        this.c = c;
        this.deadline = deadline;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Job{" +
                "c=" + c +
                ", deadline=" + deadline +
                ", profit=" + profit +
                '}';
    }
}

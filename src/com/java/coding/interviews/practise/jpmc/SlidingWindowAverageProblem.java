package com.java.coding.interviews.practise.jpmc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Window Size = 3
 * 1 -- 1
 * 10 -- (1+10)/2
 * 3 -- (1+10+3)/3
 * 5 -- (10+3+5)/3
 */
public class SlidingWindowAverageProblem {

    public static void main(String[] args) {
        SlidingWindowAverageProblem test = new SlidingWindowAverageProblem(3);
        System.out.println(test.findAverage(1));
        System.out.println(test.findAverage(10));
        System.out.println(test.findAverage(3));
        System.out.println(test.findAverage(5));

    }

    int windowSize;
    Queue<Integer> numQueue;
    double sum;

    public SlidingWindowAverageProblem(int windowSize) {
        this.windowSize = windowSize;
        this.numQueue = new LinkedList<>();
        this.sum=0d;
    }

    public double findAverage(int value){
        if(numQueue.size()==windowSize){
            sum-=numQueue.poll();
        }
        numQueue.add(value);
        sum+=value;
        return sum/numQueue.size();
    }

}

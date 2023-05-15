package com.java.coding.interviews.practise.uber;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Design a metric collector, which has provided two function:
 * add(string metricName) -> add 1 to the metric
 * getMetricCount(string metricName) -> get the count of the metric within 100s
 *
 * e.g.
 * Time
 * 1 add(Test)
 * 99 add(Test)
 * 100 getMetricCount(Test) = 2
 * 101 getMetricCount(Test) = 1
 */
public class HitCounterProblem {
    private Map<String, Deque<Integer>> queue;
    private Map<String,Deque<Pair>> dequeMap;
    public static void main(String[] args) {
        HitCounterProblem problem = new HitCounterProblem();
        problem.add("Test",1);
        problem.add("Test",99);
        System.out.println(problem.getMetric("Test",100));
        System.out.println(problem.getMetric("Test",101));
        problem.add2("Test",1);
        problem.add2("Test",99);
        System.out.println(problem.getMetric2("Test",100));
        System.out.println(problem.getMetric2("Test",101));
    }

    public HitCounterProblem() {
        this.queue = new HashMap<>();
        this.dequeMap = new HashMap<>();
    }

    public void add(String metricName,int timeStamp){
        queue.putIfAbsent(metricName,new ArrayDeque<>());
        var deQueue = queue.get(metricName);
        deQueue.add(timeStamp);
        int start = timeStamp-100+1;
        while(deQueue.getFirst()<start){
            deQueue.removeFirst();
        }
    }

    public void add2(String metricName, int timeStamp){
        dequeMap.putIfAbsent(metricName,new ArrayDeque<>());
        var deQueue = dequeMap.get(metricName);
        if(!deQueue.isEmpty() && deQueue.getFirst().timeStamp==timeStamp){
            deQueue.getFirst().count++;
        }else{
            deQueue.add(new Pair(timeStamp,1));
        }
        int start = timeStamp-100+1;
        while(deQueue.getFirst().timeStamp<start){
            deQueue.removeFirst();
        }
    }

    public int getMetric(String metricName, int timeStamp){
        if(queue.containsKey(metricName)) {
            int start = timeStamp - 100 + 1;
            var deQueue = queue.get(metricName);
            while(deQueue.getFirst()<start){
                deQueue.removeFirst();
            }
            return deQueue.size();
        }
        return 0;
    }

    public int getMetric2(String metricName, int timeStamp){
        AtomicInteger count=new AtomicInteger(0);
        if(dequeMap.containsKey(metricName)){
            int start = timeStamp-100+1;
            var deQueue = dequeMap.get(metricName);
            while(deQueue.getFirst().timeStamp<start){
                deQueue.removeFirst();
            }
            deQueue.stream().forEach(e->count.addAndGet(e.count));
        }
        return count.get();
    }

}
class Pair{
    int timeStamp;
    int count;

    public Pair(int timeStamp, int count) {
        this.timeStamp = timeStamp;
        this.count = count;
    }
}

package com.java.coding.interviews.practise.uber;

/**
 * Counter class
 *
 * Write a Counter class that will hold an expiring count for a given element. The expiration time window is defined when the class is constructed. The class should have the following methods:
 *
 *
 *
 * * put(element): Inserts an element into the counter.
 * * get(element): Returns the non-expired count of the element.
 * * getTotalCount(): Returns the total count of all elements (including repetitions).
 *
 *
 *
 * ### Example
 *
 * java
 *
 * Copy code
 *
 * Expiry: 2 min
 *
 * time - x: put('a')
 *
 * time - x + 1min: put('a')
 *
 * time - x + 1min: put('b')
 *
 * time - x + 1.5min: getTotalCount() = 3 (a, a, b)
 *
 * time - x + 2.5min: getTotalCount() = 2
 *
 * time - x + 2.5min: get('a') = 1
 */


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CounterProblem {

    private Map<String, TreeSet<Long>> map = new HashMap<>();
    private long TTL = 2000L;

    public static void main(String[] args) throws Exception {
        CounterProblem counter = new CounterProblem();
        counter.put("a");
        Thread.sleep(1000);
        counter.put("a");
        counter.put("b");
        Thread.sleep(100);
        System.out.println(counter.totalCount());
        Thread.sleep(1000);
        System.out.println(counter.totalCount());
        System.out.println(counter.get("a"));

    }

    public void put(String value){
        long now = System.currentTimeMillis();
        map.putIfAbsent(value,new TreeSet<>());
        map.get(value).add(now);
        removeOldTimeStamps(map.get(value));
    }

    public int get(String value){
        if(map.containsKey(value)){
            removeOldTimeStamps(map.get(value));
            return map.get(value).size();
        }
        return 0;
    }

    private void removeOldTimeStamps(TreeSet<Long> timestamps){
        long now = System.currentTimeMillis();
        timestamps.removeIf(e->e+TTL<now);
    }

    public int totalCount(){
        int totalCount=0;
        for(var entry : map.entrySet()){
            removeOldTimeStamps(entry.getValue());
            if(entry.getValue().size()>0)
                totalCount+=entry.getValue().size();
            else
                map.remove(entry.getKey());
        }
        return totalCount;
    }

}

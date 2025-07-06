package com.java.coding.interviews.practise.confluent;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Given a window size,
 * perform get, put, and average operation
 * items that were added before the window size should be removed while taking average as well, and also during get operation, return null if item expired.
 * window size 1hr
 * 00:00 put("A",10)
 * 00:10 put("B",20)
 * 00:30 average() -> 15
 * 01:05 average () -> 20
 * 01:08 get("B") -> 20
 * 01:15 put("A",30)
 * 01:50 average -> 30
 */
public class TimeAwareCache {

    public static void main(String[] args) throws Exception{
        put("A",10, LocalTime.now().minusSeconds(5));
        put("B",20, LocalTime.now().minusSeconds(3));
        System.out.println(average());
        put("A",15, LocalTime.now().minusSeconds(5));
        System.out.println(average());
        System.out.println(get("A",LocalTime.now()));
        TimeUnit.SECONDS.sleep(6);
        System.out.println(average());
    }
    private static Duration WINDOW = Duration.ofSeconds(10);
    private static Map<String,TreeMap<LocalTime,List<Integer>>> store = new HashMap<>();
    private static void expireOldItems(LocalTime now){
        List<String> emptyKeys = new ArrayList<>();
        LocalTime cutoff = now.minus(WINDOW);
        var iterator = store.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,TreeMap<LocalTime,List<Integer>>> entry = iterator.next();
            TreeMap<LocalTime,List<Integer>> timeEntry = entry.getValue();
            timeEntry.headMap(cutoff,false).clear();
            if(timeEntry.isEmpty()){
                iterator.remove();
            }
        }
    }

    public static void put(String key, int value, LocalTime now){
        expireOldItems(now);
        store.putIfAbsent(key, new TreeMap<>());
        store.get(key).putIfAbsent(now, new ArrayList<>());
        store.get(key).get(now).add(value);
    }
    public static List<Integer> get(String key, LocalTime now){
        List<Integer> result = new ArrayList<>();
        expireOldItems(now);
        store.get(key).values().stream().forEach(e->result.addAll(e));
        return result;
    }

    public static Double average(){
        expireOldItems(LocalTime.now());
        double sum = 0.0;
        if(store.size() == 0){ return null;}
        System.out.println(store);
        for(var entry : store.entrySet()){
            for(var value : entry.getValue().entrySet()){
                sum += value.getValue().stream().reduce((a,b)->a+b).get();
            }
        }
        return sum / store.size();
    }
}

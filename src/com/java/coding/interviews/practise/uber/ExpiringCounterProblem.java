package com.java.coding.interviews.practise.uber;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Design a data structure that counts the number of times an element is added,
 * but only keeps track of additions within a specified time-to-live (TTL) period.
 * The TTL is given in milliseconds.
 *
 * The data structure should support the following operations:
 * 1. put(element): Adds an element to the counter.
 * 2. getCount(element): Returns the count of the element within the TTL.
 * 3. getTotalCount(): Returns the total count of all elements within their respective TTLs.
 *
 * Questions to consider:
 * 1. Should multiple put() calls with the same element be counted separately or deduplicated?
 * 2. How should the data structure handle concurrent access?
 * 3. Is this expected to work in a multi-threaded environment?
 * Do we expect more reads (get_count) or writes (put)?
 */

//Multi Threaded
public class ExpiringCounterProblem {

    private Long TTL;
    private Map<String, ConcurrentLinkedDeque<Long>> map;

    public ExpiringCounterProblem(Long ttl) {
        this.TTL = ttl;
        this.map = new HashMap<>();
    }

    //Time Complexity: O(1)
    public void put(String element){
        long now = System.currentTimeMillis();
        map.computeIfAbsent(element, k-> new ConcurrentLinkedDeque<>()).add(now);
    }

    //Time Complexity: O(K) where K is the number of timestamps for the element
    public int getCount(String element){
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = map.get(element);
        if(timestamps==null)
            return 0;
         cleanExpired(timestamps,now);
         return timestamps.size();
        }
        //Time Complexity: O(N * K) where N is the number of elements and K is the average number of timestamps per element
        public int getTotalCount(){
            long now = System.currentTimeMillis();
            int count = 0;
            for(var entry : map.entrySet()){
                cleanExpired(entry.getValue(), now);
                count+= entry.getValue().size();
            }
            return count;
        }

    private void cleanExpired(Deque<Long> timestamps, long now) {
            timestamps.removeIf(k ->  k<=now-TTL);
        }

    public static void main(String[] args)  throws InterruptedException {
        ExpiringCounterProblem counter = new ExpiringCounterProblem(2000L); // TTL = 5 seconds

        Runnable putTask = () -> {
            for (int i = 0; i < 100; i++) {
                counter.put("x");
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        };

        Runnable getTask = () -> {
            for (int i = 0; i < 20; i++) {
                int count = counter.getCount("x");
                System.out.println("Count: " + count);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        };

        Thread t1 = new Thread(putTask);
        Thread t2 = new Thread(getTask);
        Thread t3 = new Thread(getTask);

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        System.out.println("Final count: " + counter.getCount("x"));
        System.out.println("Total count: " + counter.getTotalCount());
    }
}

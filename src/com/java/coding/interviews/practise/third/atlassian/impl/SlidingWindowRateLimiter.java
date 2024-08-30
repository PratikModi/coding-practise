package com.java.coding.interviews.practise.third.atlassian.impl;

import com.java.coding.interviews.practise.third.atlassian.IRateLimiter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Questions:-
 * ==========
 * 1. Are you going to put default limit for customer?
 * 2. Do we need to take care of concurrent access?
 * 3. I will keep asking question if I have any while coding
 *
 * Talking Points:-
 * ===============
 * 1. We will discuss multiple approaches of implementation
 * 2. We will also discuss how we can run in distributed environment
 *
 * There are actually multiple algorithms to implement the rate limiter. All solve the same problem. But all have pros and cons.
 * we will discuss few algorithms and will implement one of them.
 *
 * Algorithm-1:-
 * ===========
 * 1. Token Bucket:-
 *    ------------
 *    1. Bucket is container with some pre-defined capacity.
 *    2. Tokens are put in bucket at preset rate.
 *
 *    Two Parameters:-
 *    ---------------
 *    1. Bucket Size
 *    2. Refill rate.
 *
 *    Cons:-
 *    -----
 *    1. Since it takes two parameters, it might not be tune them properly.
 *
 * 2. Leaky Bucket:-
 *    -------------
 *    1. Implemented using fixed size queue. It's similar to Token Bucket.
 *    2. Once the request comes, it checks if queue is full, if so request is dropped otherwise put it into the queue.
 *    3. Requests are pulled and processed from queue at regular internals.
 *
 *    This algorithm is important if you want to control the outflow processing.
 *
 *    Two Parameters:-
 *    ---------------
 *    1. Bucket Size
 *    2. Refill rate.
 *
 *    Cons:-
 *    -----
 *    1. Since it takes two parameters, it might not be tune them properly.
 *
 * 3. Fixed Window:-
 *    -------------
 *    1. This algorithm divides the timelines into fixed size time window and assigns a counter for each window.
 *    2. Each request increments the counter by one. Once counters are over requests will be dropped.
 *
 *    Cons:-
 *    ----
 *    1. It allows more requests to go through at the edge of a window.
 *
 * 4. Sliding Window:-
 *    --------------
 *    1. This algorithm keeps track of request timestamps.
 *    2. When new request comes in, remove all outdated timestamps.
 *    3. Add the timestamp of the new request to the log.
 *    4. If log size is same or lower than the allowed limit, request is accepted otherwise it is rejected.
 *
 *    Question:-
 *    --------
 *    1. Do we need to allow client to send the timestamp? Like client can send time when the request was actually made.
 */
public class SlidingWindowRateLimiter implements IRateLimiter {

    private int limit;
    private Long time;
    private TimeUnit timeUnit;
    private Map<Integer, List<Long>> customerRateLimiterMap;
    private Supplier<Long> requestTime;

    public SlidingWindowRateLimiter(int limit, Long time, TimeUnit timeUnit, Supplier<Long> requestTime) {
        this.limit = limit;
        this.time = time;
        this.timeUnit = timeUnit;
        this.requestTime = requestTime;
        this.customerRateLimiterMap = new ConcurrentHashMap<>(); //Managing Concurrency
    }

    public SlidingWindowRateLimiter(int limit, Long time, TimeUnit timeUnit) {
        this.limit = limit;
        this.time = time;
        this.timeUnit = timeUnit;
        this.customerRateLimiterMap = new ConcurrentHashMap<>();
    }

    public SlidingWindowRateLimiter() {
        this.limit=5;
        this.time=1L;
        this.timeUnit=TimeUnit.SECONDS;
        this.customerRateLimiterMap = new ConcurrentHashMap<>();
    }

    public SlidingWindowRateLimiter(Supplier<Long> requestTime) {
        this.limit=5;
        this.time=1L;
        this.timeUnit=TimeUnit.SECONDS;
        this.customerRateLimiterMap = new ConcurrentHashMap<>();
        this.requestTime = requestTime;
    }

    @Override
    public boolean checkLimit(int customerId) {
        Long reqTime = Instant.now().toEpochMilli();
        if(requestTime!=null){
            reqTime = requestTime.get();
        }
        if(!customerRateLimiterMap.containsKey(customerId)){
            List<Long> requestTimes = new ArrayList<>();
            requestTimes.add(reqTime);
            customerRateLimiterMap.put(customerId,requestTimes);
            return true;
        }else{
            List<Long> requestTimes = customerRateLimiterMap.get(customerId);
            requestTimes.add(reqTime);
            if(requestTimes.size()<=limit)
                return true;
            else{
                Long windowTime = reqTime - timeUnit.toMillis(time);
                requestTimes.removeIf(t->t<windowTime);
                if(requestTimes.size()<=limit) // This can be ignored by putting TTL so that it will always have required entries
                    return true;
            }
        }
        return false;
    }
}

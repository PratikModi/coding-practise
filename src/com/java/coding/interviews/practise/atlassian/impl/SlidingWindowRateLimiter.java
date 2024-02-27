package com.java.coding.interviews.practise.atlassian.impl;

import com.java.coding.interviews.practise.atlassian.Constants;
import com.java.coding.interviews.practise.atlassian.IRateLimiter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Questions:
 * 1. Are we going to put default limit for customer?
 * 2. Do we need to take care of concurrent access?
 * 3. Do we need to allow customer to send log time? Should it be when client send request or when server receive the request?
 * 4. I believe it should be real time shouldn't increase the latency

 * The algorithm keeps track of request timestamps. Timestamp data is usually kept in cache, such as sorted sets of Redis [8].
 * When a new request comes in, remove all the outdated timestamps. Outdated timestamps are defined as those older than the start of the current time window. *
 * Add timestamp of the new request to the log.
 * If the log size is the same or lower than the allowed count, a request is accepted. Otherwise, it is rejected.

 * Talking Points:-
 * ---------------
 * Set Agenda:-
 * -----------
 * 1. We will implement the algorithm.
 * 2. We will test the algorithm.
 * 3. We will discuss how can we run this in distributed environment.
 *
 */
public class SlidingWindowRateLimiter implements IRateLimiter {

    private Map<Integer, List<Long>> slidingWindowTrackerPerClient;
    private int limit;
    private Long time;
    private TimeUnit timeUnit;

    public SlidingWindowRateLimiter(int limit, Long time, TimeUnit timeUnit) {
        this.limit = limit;
        this.time = time;
        this.timeUnit = timeUnit;
        this.slidingWindowTrackerPerClient = new ConcurrentHashMap<>();
    }

    public SlidingWindowRateLimiter() {
        this.limit = Constants.DEFAULT_LIMIT;
        this.time = Constants.DEFAULT_TIME;
        this.timeUnit = Constants.DEFAULT_TIMEUNIT;
        this.slidingWindowTrackerPerClient = new ConcurrentHashMap<>();
    }

    @Override
    public boolean checkLimit(int customerId) {
        Long currentTimeInMillis = Instant.now().toEpochMilli();
        if(!slidingWindowTrackerPerClient.containsKey(customerId)){
            List<Long> logTime = new ArrayList<>();
            logTime.add(currentTimeInMillis);
            slidingWindowTrackerPerClient.put(customerId,logTime);
            return true;
        }else{
            List<Long> requestLogs = slidingWindowTrackerPerClient.get(customerId);
            requestLogs.add(currentTimeInMillis);
            if(requestLogs.size()<=limit){
                return true;
            }else{
                //System.out.println(requestLogs);
                Long slidingWindow = currentTimeInMillis - convertTime(time);
                requestLogs.removeIf(t->t<slidingWindow);
                /*List<Long> arrivalTimes = slidingWindowTrackerPerClient.get(customerId);
                Iterator<Long> iterator = arrivalTimes.iterator();
                while(iterator.hasNext()){
                    Long arrivalTime = iterator.next();
                    if(arrivalTime<slidingWindow){
                        iterator.remove();
                    }
                }*/
                if(requestLogs.size()<=limit){
                    return true;
                }
                return false;
            }
        }
    }

    private Long convertTime(Long time){
         return timeUnit.toMillis(time);
    }
}

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
                List<Long> arrivalTimes = slidingWindowTrackerPerClient.get(customerId);
                Iterator<Long> iterator = arrivalTimes.iterator();
                while(iterator.hasNext()){
                    Long arrivalTime = iterator.next();
                    if(arrivalTime<slidingWindow){
                        iterator.remove();
                    }
                }
                if(arrivalTimes.size()<=limit){
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

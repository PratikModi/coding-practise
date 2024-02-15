package com.java.coding.interviews.practise.second.atlassian;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SlidingWindowRateLimiter implements IRateLimiter{

    private int limit;
    private Long time;
    private TimeUnit timeUnit;
    private Map<Integer, List<Long>> slidingWindowMap;

    public SlidingWindowRateLimiter(int limit, Long time, TimeUnit timeUnit) {
        this.limit = limit;
        this.time = time;
        this.timeUnit = timeUnit;
        this.slidingWindowMap = new ConcurrentHashMap<>();
    }

    public SlidingWindowRateLimiter() {
        this.slidingWindowMap = new ConcurrentHashMap<>();
        this.limit = Constants.limit;
        this.time = Constants.time;
        this.timeUnit = Constants.timeunit;
    }

    @Override
    public boolean checkLimit(int customerId) {
        Long logtime = Instant.now().toEpochMilli();
        if(!slidingWindowMap.containsKey(customerId)){
            List<Long> logTimes = new ArrayList<>();
            logTimes.add(logtime);
            slidingWindowMap.put(customerId,logTimes);
            return true;
        }else{
            List<Long> logTimes = slidingWindowMap.get(customerId);
            logTimes.add(logtime);
            if(logTimes.size()<=limit){
                return true;
            }else{
                Long slidingWindow = logtime - timeUnit.toMillis(time);
                Iterator<Long> iterator = logTimes.iterator();
                while(iterator.hasNext()){
                    Long requestTime = iterator.next();
                    if(requestTime<=slidingWindow)
                        iterator.remove();
                }
                if(logTimes.size()<=limit){
                    return true;
                }
            }
        }
        return false;
    }

    public static void testCustomerLimit(int customerId) throws Exception{
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(5, 1L, TimeUnit.SECONDS);
        boolean[] result = new boolean[6];
        for(int i=0;i<result.length;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            result[i]=rateLimiter.checkLimit(customerId);
        }
        System.out.println(Arrays.toString(result));
    }

    public static void testCustomerLimit2(int customerId) throws Exception{
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(5, 1L, TimeUnit.SECONDS);
        boolean[] result = new boolean[6];
        for(int i=0;i<result.length;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            result[i]=rateLimiter.checkLimit(customerId);
        }
        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) throws Exception {
        testCustomerLimit(1);
    }

}

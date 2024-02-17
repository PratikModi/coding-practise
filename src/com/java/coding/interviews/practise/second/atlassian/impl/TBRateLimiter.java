package com.java.coding.interviews.practise.second.atlassian.impl;

import com.java.coding.interviews.practise.second.atlassian.IRateLimiter;
import com.java.coding.interviews.practise.second.atlassian.IRefillStrategy;
import com.java.coding.interviews.practise.second.atlassian.ITokenBucket;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TBRateLimiter implements IRateLimiter {

    private Map<Integer, ITokenBucket> rateLimiterPerCustomer;
    private long capacity;
    private long tokenPerDuration;
    private long duration;
    private TimeUnit unit;

    public TBRateLimiter(long capacity, long tokenPerDuration, long duration, TimeUnit unit) {
        this.capacity = capacity;
        this.tokenPerDuration = tokenPerDuration;
        this.duration = duration;
        this.unit = unit;
        this.rateLimiterPerCustomer = new ConcurrentHashMap<>();
    }

    @Override
    public boolean checkLimit(int customerId) {
        rateLimiterPerCustomer.putIfAbsent(customerId,createTokenBucket(capacity,tokenPerDuration,duration,unit));
        return rateLimiterPerCustomer.get(customerId).tryConsume();
    }

    private ITokenBucket createTokenBucket(long capacity,long tokenPerDuration,long duration,TimeUnit timeUnit){
        IRefillStrategy refillStrategy = new FixedPeriodRefillStrategy(tokenPerDuration, duration, timeUnit);
        ITokenBucket tokenBucket = new TokenBucketRateLimiter(capacity,refillStrategy,capacity);
        return tokenBucket;
    }

    public static void main(String[] args) {
        TBRateLimiter limiter = new TBRateLimiter(5,5,5,TimeUnit.SECONDS);
        boolean[] result = new boolean[6];
        for(int i=0;i<result.length;i++){
            result[i] = limiter.checkLimit(1);
        }
        System.out.println(Arrays.toString(result));
    }
}

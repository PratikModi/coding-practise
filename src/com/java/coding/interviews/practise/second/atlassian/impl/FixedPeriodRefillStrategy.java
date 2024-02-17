package com.java.coding.interviews.practise.second.atlassian.impl;

import com.java.coding.interviews.practise.second.atlassian.IRefillStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class FixedPeriodRefillStrategy implements IRefillStrategy {

    private long tokenPerDuration;
    private long duration;
    private long lastRefillTime;
    private long nextRefillTime;
    private TimeUnit timeUnit;

    public FixedPeriodRefillStrategy(long tokenPerDuration, long duration, TimeUnit timeUnit) {
        this.tokenPerDuration = tokenPerDuration;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.lastRefillTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.nextRefillTime = lastRefillTime+timeUnit.toMillis(duration);
    }
    @Override
    public long refill() {
        long now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        if(now<nextRefillTime)
            return 0;
        long numPeriods = Math.max(0,(now-lastRefillTime)/duration);
        lastRefillTime+=numPeriods*duration;
        nextRefillTime+=lastRefillTime+duration;
        return numPeriods*tokenPerDuration;
    }

    @Override
    public long durationForNextRefill() {
        long now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        return timeUnit.toMillis(Math.max(0,(nextRefillTime-now)));
    }
}

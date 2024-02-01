package com.java.coding.interviews.practise.atlassian.impl;

import com.java.coding.interviews.practise.atlassian.IRefillStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class FixedIntervalRefillStrategy implements IRefillStrategy {

    private final long numTokenPerPeriod;
    private final long periodDuration;
    private long lastRefillTime;
    private long nextRefillTime;

    public FixedIntervalRefillStrategy(long numTokenPerPeriod, long periodDuration, TimeUnit unit) {
        this.numTokenPerPeriod = numTokenPerPeriod;
        this.periodDuration = periodDuration;
        this.lastRefillTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.nextRefillTime = lastRefillTime + unit.toMillis(periodDuration);
    }

    @Override
    public long refill() {
        long now = ZonedDateTime.of(LocalDateTime.now(),ZoneId.systemDefault()).toInstant().toEpochMilli();
        if(now < nextRefillTime)
            return 0;
        long numPeriods = Math.max(0,(now-lastRefillTime)/periodDuration);
        lastRefillTime += numPeriods*periodDuration;
        nextRefillTime += lastRefillTime+periodDuration;
        return numTokenPerPeriod*numPeriods;
    }

    @Override
    public long durationUntilNextRefill(TimeUnit unit) {
        long now = ZonedDateTime.of(LocalDateTime.now(),ZoneId.systemDefault()).toInstant().toEpochMilli();
        return unit.toMillis(Math.max(0,nextRefillTime-now));
    }
}

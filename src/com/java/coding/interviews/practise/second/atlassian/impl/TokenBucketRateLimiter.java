package com.java.coding.interviews.practise.second.atlassian.impl;

import com.java.coding.interviews.practise.second.atlassian.IRefillStrategy;
import com.java.coding.interviews.practise.second.atlassian.ITokenBucket;

public class TokenBucketRateLimiter implements ITokenBucket {

    private long capacity;
    private IRefillStrategy refillStrategy;
    private long size;

    public TokenBucketRateLimiter(long capacity, IRefillStrategy refillStrategy, long initialCapacity) {
        this.capacity = capacity;
        this.refillStrategy = refillStrategy;
        this.size = initialCapacity;
    }

    @Override
    public long capacity() {
        return this.capacity;
    }

    @Override
    public long availableTokens() {
        refill(refillStrategy.refill());
        return size;
    }

    @Override
    public boolean tryConsume() {
        return tryConsume(1);
    }

    @Override
    public boolean tryConsume(int numToken) {
        if(numToken<=0 || numToken>capacity)
            return false;
        refill(refillStrategy.refill());
        if(numToken<=size){
            size-=numToken;
            return true;
        }
        return false;
    }

    @Override
    public void consume() {
        consume(1);
    }

    @Override
    public void consume(int numToken) {
        while(tryConsume(numToken)) {
            break;
        }
    }

    private synchronized void refill(long numTokens){
        long newTokens = Math.min(capacity,Math.max(0,numTokens));
        this.size = Math.max(0,Math.min(newTokens+size,capacity));
    }
}

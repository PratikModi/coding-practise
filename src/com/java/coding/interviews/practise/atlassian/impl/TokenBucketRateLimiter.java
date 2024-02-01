package com.java.coding.interviews.practise.atlassian.impl;

import com.java.coding.interviews.practise.atlassian.IRefillStrategy;
import com.java.coding.interviews.practise.atlassian.ITokenBucket;

public class TokenBucketRateLimiter implements ITokenBucket {

    private final long capacity;
    private final IRefillStrategy refillStrategy;
    private long size;

    public TokenBucketRateLimiter(long capacity, IRefillStrategy refillStrategy, long initialCapacity) {
        this.capacity = capacity;
        this.refillStrategy = refillStrategy;
        this.size = initialCapacity;
    }

    @Override
    public long capacity() {
        return capacity;
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
        if(numToken<=0 || numToken > capacity)
            return false;
        refill(refillStrategy.refill());
        if(numToken <= size){
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
        while(tryConsume(1)){
            break;
        }

        //Sleep
    }

    public synchronized void refill(long numTokens){
        long newTokens = Math.min(capacity,Math.max(0,numTokens));
        this.size = Math.max(0,Math.min(size+newTokens,capacity));
    }
}

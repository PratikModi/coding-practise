package com.java.coding.interviews.practise.circuitbreaker;

import java.time.LocalDateTime;

public class BreakerClosedState implements BreakerStateInterface{

    private final CircuitBreaker circuitBreaker;
    private final int slidingWindowSize; //Count Based Circuit Breaker others could be Time Based
    private int callCountBucket[];
    private int failureCallCountBucket[];
    private int slowCallDurationCountBucket[];
    private long lastCallTimeStampInSec=0;
    private CountStats countStats;

    public BreakerClosedState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.slidingWindowSize = circuitBreaker.getCircuitBreakerConfig().getSlidingWindowSize();
        countStats = new CountStats();
        clearAllBucket();
    }

    private void clearAllBucket(){
        callCountBucket = new int[slidingWindowSize];
        failureCallCountBucket = new int[slidingWindowSize];
        slowCallDurationCountBucket = new int[slidingWindowSize];
        lastCallTimeStampInSec = (System.currentTimeMillis()/1000);
    }

    private void clearBucket(int timeInSec){
        int bucket = timeInSec % slidingWindowSize;
        countStats.callCount-=callCountBucket[bucket];
        callCountBucket[bucket]=0;
        countStats.failureCount-=failureCallCountBucket[bucket];
        failureCallCountBucket[bucket]=0;
        countStats.slowCount-=slowCallDurationCountBucket[bucket];
        slowCallDurationCountBucket[bucket]=0;
    }

    @Override
    public boolean isClosedForThisCall() {
        return true;
    }

    @Override
    public void callFailed(long duration) {
        callFailedOrSucceeded(duration,true);
    }

    @Override
    public void callSucceeded(long duration) {
        callFailedOrSucceeded(duration,false);
    }

    private void callFailedOrSucceeded(long duration, boolean isFailureCall){
        boolean isSlowCall = circuitBreaker.isSlowCall(duration);
        long nowInSec = System.currentTimeMillis()/1000;
        if(lastCallTimeStampInSec==nowInSec) {
            addToLastCallBucket(isFailureCall, isSlowCall);
        }
        else{
            //compared to lastCallTimestampInSec, we moved next second or more
            if(nowInSec-lastCallTimeStampInSec>=slidingWindowSize){
                clearAllBucket();
            }else{
                //Only few buckets need to be clear -- Sliding Window like rate limiter remove older time stamps
                for(int timeInSec = (int)lastCallTimeStampInSec+1;timeInSec<nowInSec;timeInSec++){
                    clearBucket(timeInSec);
                }
            }
        }
        //Check now if we need to move to open state
        if(countStats.callCount>=circuitBreaker.getCircuitBreakerConfig().getMinimumNumberOfCalls()){
            if(circuitBreaker.isExceedFailureOrSlowCallThreshold(countStats)){
                circuitBreaker.moveToOpenState();
            }
        }

    }

    @Override
    public BreakerStateType getBreakerState() {
        return BreakerStateType.CLOSED;
    }

    private void addToLastCallBucket(boolean isFailureCall, boolean isSlowCall){
        countStats.callCount++;
        if(isFailureCall)
            countStats.failureCount++;
        if(isSlowCall)
            countStats.slowCallRate++;
        int lastCallTimeStampBucket = (int)lastCallTimeStampInSec%slidingWindowSize;
        callCountBucket[lastCallTimeStampBucket]++;
        if(isFailureCall)
            failureCallCountBucket[lastCallTimeStampBucket]++;
        if(isSlowCall)
            slowCallDurationCountBucket[lastCallTimeStampBucket]++;
    }


}

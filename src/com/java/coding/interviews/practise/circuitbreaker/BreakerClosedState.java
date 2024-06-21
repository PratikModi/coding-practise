package com.java.coding.interviews.practise.circuitbreaker;

import java.time.LocalDateTime;

public class BreakerClosedState implements BreakerStateInterface{

    private final CircuitBreaker circuitBreaker;
    private final int slidingWindowSize; //Count Based Circuit Breaker others could be Time Based
    private int callCountBucket[];
    private int failureCallCountBucket[];
    private int slowCallDurationCountBucket[];
    private long lastCallTimeStampInSec=0;

    public BreakerClosedState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.slidingWindowSize = circuitBreaker.getCircuitBreakerConfig().getSlidingWindowSize();
        clearBucket();
    }

    private void clearBucket(){
        callCountBucket = new int[slidingWindowSize];
        failureCallCountBucket = new int[slidingWindowSize];
        slowCallDurationCountBucket = new int[slidingWindowSize];
        lastCallTimeStampInSec = (System.currentTimeMillis()/1000);
    }

    @Override
    public boolean isClosedForThisCall() {
        return true;
    }

    @Override
    public void callFailed(long duration) {

    }

    @Override
    public void callSucceeded(long duration) {

    }

    private void callFailedOrSucceeded(long duration, boolean isFailureCall){
        boolean isSlowCall = circuitBreaker.isSlowCall(duration);
        long nowInSec = System.currentTimeMillis()/1000;
        //if(lastCallTimeStampInSec==)
    }

    @Override
    public BreakerStateType getBreakerState() {
        return BreakerStateType.CLOSED;
    }


}

package com.java.coding.interviews.practise.circuitbreaker;

public class CircuitBreaker {

    private final CircuitBreakerConfig circuitBreakerConfig;
    private BreakerStateInterface breakerStateInterface;

    public CircuitBreaker(CircuitBreakerConfig circuitBreakerConfig) {
        this.circuitBreakerConfig = circuitBreakerConfig.clone();
    }

    public synchronized boolean isClosedForThisCall(){
        return breakerStateInterface.isClosedForThisCall();
    }

    public synchronized void callFailed(long duration){
        breakerStateInterface.callFailed(duration);
    }

    public synchronized void callSucceeded(long duration){
        breakerStateInterface.callSucceeded(duration);
    }

    public BreakerStateType getBreakerState(){
        return breakerStateInterface.getBreakerState();
    }

    public CircuitBreakerConfig getCircuitBreakerConfig() {
        return circuitBreakerConfig;
    }

    public void moveToClosedState(){
        this.breakerStateInterface = new BreakerClosedState(this);
    }

    public void moveToOpenState(){
        this.breakerStateInterface=new BreakerOpenState(this);
    }

    public void moveToHalfOpenState(){
        this.breakerStateInterface=new BreakerHalfOpenState(this);
    }

    public boolean isSlowCall(long duration){
        if(circuitBreakerConfig.getSlowCallRateDurationThresholdCount()<=duration)
            return true;
        return false;
    }

    public boolean isExceedFailureOrSlowCallThreshold(CountStats countStats){
        countStats.calculateRates();
        if(circuitBreakerConfig.getFailureRateThreshold()<=countStats.failureRate)
            return true;
        if(circuitBreakerConfig.getSlowCallRateThresholdCount()<=countStats.slowCallRate)
            return true;
        return false;
    }
}

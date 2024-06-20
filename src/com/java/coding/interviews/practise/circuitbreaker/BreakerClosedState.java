package com.java.coding.interviews.practise.circuitbreaker;

public class BreakerClosedState implements BreakerStateInterface{

    private final CircuitBreaker circuitBreaker;
    private final int slidingWindowSize;

    public BreakerClosedState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.slidingWindowSize = circuitBreaker.getCircuitBreakerConfig().getSlidingWindowSize();
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

    @Override
    public BreakerStateType getBreakerState() {
        return BreakerStateType.CLOSED;
    }
}

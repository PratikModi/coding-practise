package com.java.coding.interviews.practise.circuitbreaker;

public class BreakerOpenState implements BreakerStateInterface{

    private final CircuitBreaker circuitBreaker;
    private long openStateEndTimeStamp;

    public BreakerOpenState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.openStateEndTimeStamp = System.currentTimeMillis()+circuitBreaker.getCircuitBreakerConfig().getWaitDurationInOpenStateDefault();
    }

    @Override
    public boolean isClosedForThisCall() {

        if(System.currentTimeMillis()>=openStateEndTimeStamp){
            circuitBreaker.moveToHalfOpenState();
            return circuitBreaker.isClosedForThisCall();
        }
        return false;
    }

    @Override
    public BreakerStateType getBreakerState() {
        return BreakerStateType.OPEN;
    }
}

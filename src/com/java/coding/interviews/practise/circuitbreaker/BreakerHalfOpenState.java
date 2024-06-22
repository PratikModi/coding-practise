package com.java.coding.interviews.practise.circuitbreaker;

public class BreakerHalfOpenState implements BreakerStateInterface{

    private final CircuitBreaker circuitBreaker;
    private CountStats countStats;
    private int permittedNumberOfCallsSoFar=0;
    private long lastOpenCallTimeLimit=0;

    public BreakerHalfOpenState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
        this.countStats = new CountStats();
    }

    @Override
    public boolean isClosedForThisCall() {
        if(permittedNumberOfCallsSoFar<circuitBreaker.getCircuitBreakerConfig().getPermittedNumberOfCallsInHalfOpenState()){
            permittedNumberOfCallsSoFar++;
            return true;
        }
        //There is a time  limit. Check if it is set. If not, it is the first call. Return false
        if(lastOpenCallTimeLimit==0){
            lastOpenCallTimeLimit = System.currentTimeMillis()+circuitBreaker.getCircuitBreakerConfig().getMaxDurationInHalfOpenStateDefault();
            return false;
        }
        if(System.currentTimeMillis()>=lastOpenCallTimeLimit){
            circuitBreaker.moveToClosedState();
            return circuitBreaker.isClosedForThisCall();
        }
        return false;
    }

    @Override
    public void callFailed(long duration) {
        countStats.failureCount++;
        callFailedOrSucceeded(duration);
    }

    @Override
    public void callSucceeded(long duration) {
        callFailedOrSucceeded(duration);
    }

    @Override
    public BreakerStateType getBreakerState() {
        return BreakerStateType.HALF_OPEN;
    }

    private void callFailedOrSucceeded(long duration){
        countStats.callCount++;
        if(circuitBreaker.isSlowCall(duration)){
            countStats.slowCount++;
        }
        if(countStats.callCount<circuitBreaker.getCircuitBreakerConfig().getPermittedNumberOfCallsInHalfOpenState()){
            return;
        }
        if(circuitBreaker.isExceedFailureOrSlowCallThreshold(countStats)){
            circuitBreaker.moveToOpenState();
        }else{
            circuitBreaker.moveToClosedState();
        }
    }



}

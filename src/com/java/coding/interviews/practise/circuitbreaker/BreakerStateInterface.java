package com.java.coding.interviews.practise.circuitbreaker;


public interface BreakerStateInterface {
    //to check the state of the breaker for this current call
    boolean isClosedForThisCall();
    //to inform the breaker that the call failed
    void callFailed(long duration);
    // to inform the breaker that the call succeeded
    void callSucceeded(long duration);
    BreakerStateType getBreakerState();
}

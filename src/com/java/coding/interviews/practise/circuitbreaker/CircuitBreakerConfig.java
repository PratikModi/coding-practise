package com.java.coding.interviews.practise.circuitbreaker;

public class CircuitBreakerConfig {

    private static final float FAILURE_RATE_THRESHOLD_COUNT=50;
    private static final float SLOW_CALL_RATE_THRESHOLD_COUNT=100;
    private static final long SLOW_CALL_RATE_DURATION_THRESHOLD_COUNT=60000;
    private static final int PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE=10;
    private static final int SLIDING_WINDOW_SIZE_DEFAULT=100;
    private static final long WAIT_DURATION_IN_OPEN_STATE_DEFAULT=60000;
    private static final long MAX_DURATION_IN_HALF_OPEN_STATE_DEFAULT=120000;

    private float failureRateThreshold = FAILURE_RATE_THRESHOLD_COUNT;
    private float slowCallRateThresholdCount=SLOW_CALL_RATE_THRESHOLD_COUNT;
    private long slowCallRateDurationThresholdCount =SLOW_CALL_RATE_DURATION_THRESHOLD_COUNT;
    private int permittedNumberOfCallsInHalfOpenState=PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE;
    private int slidingWindowSize=SLIDING_WINDOW_SIZE_DEFAULT;
    private long waitDurationInOpenStateDefault=WAIT_DURATION_IN_OPEN_STATE_DEFAULT;
    private long maxDurationInHalfOpenStateDefault =MAX_DURATION_IN_HALF_OPEN_STATE_DEFAULT;

    public CircuitBreakerConfig() {
    }

    public CircuitBreakerConfig clone(){
        CircuitBreakerConfig clone = new CircuitBreakerConfig();
        clone.setFailureRateThreshold(failureRateThreshold);
        clone.setSlowCallRateThresholdCount(slowCallRateThresholdCount);
        clone.setPermittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState);
        clone.setSlidingWindowSize(slidingWindowSize);
        clone.setWaitDurationInOpenStateDefault(waitDurationInOpenStateDefault);
        clone.setMaxDurationInHalfOpenStateDefault(maxDurationInHalfOpenStateDefault);
        return clone;
    }

    public float getFailureRateThreshold() {
        return failureRateThreshold;
    }

    public void setFailureRateThreshold(float failureRateThreshold) {
        this.failureRateThreshold = failureRateThreshold;
    }

    public float getSlowCallRateThresholdCount() {
        return slowCallRateThresholdCount;
    }

    public void setSlowCallRateThresholdCount(float slowCallRateThresholdCount) {
        this.slowCallRateThresholdCount = slowCallRateThresholdCount;
    }

    public long getSlowCallRateDurationThresholdCount() {
        return slowCallRateDurationThresholdCount;
    }

    public void setSlowCallRateDurationThresholdCount(long slowCallRateDurationThresholdCount) {
        this.slowCallRateDurationThresholdCount = slowCallRateDurationThresholdCount;
    }

    public int getPermittedNumberOfCallsInHalfOpenState() {
        return permittedNumberOfCallsInHalfOpenState;
    }

    public void setPermittedNumberOfCallsInHalfOpenState(int permittedNumberOfCallsInHalfOpenState) {
        this.permittedNumberOfCallsInHalfOpenState = permittedNumberOfCallsInHalfOpenState;
    }

    public int getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public long getWaitDurationInOpenStateDefault() {
        return waitDurationInOpenStateDefault;
    }

    public void setWaitDurationInOpenStateDefault(long waitDurationInOpenStateDefault) {
        this.waitDurationInOpenStateDefault = waitDurationInOpenStateDefault;
    }

    public long getMaxDurationInHalfOpenStateDefault() {
        return maxDurationInHalfOpenStateDefault;
    }

    public void setMaxDurationInHalfOpenStateDefault(long maxDurationInHalfOpenStateDefault) {
        this.maxDurationInHalfOpenStateDefault = maxDurationInHalfOpenStateDefault;
    }


}

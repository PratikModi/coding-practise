package com.java.coding.interviews.practise.wise;

import java.util.concurrent.Callable;

public class CircuitBreaker {

    enum State{
        OPEN, HALF_OPEN,CLOSED;
    }

    public static void main(String[] args) throws Exception{
        CircuitBreaker cb = new CircuitBreaker(3,3000);
        for (int i = 0; i <10 ; i++) {
            try {
                String result = cb.execute(() -> {
                    if (Math.random() < 0.5) throw new RuntimeException("API Failed");
                    return "API SUCCESS";
                });
                System.out.println("Call  "+i+" succeed: "+result);
            }catch (Exception e){
                System.out.println("Call "+i+ " Failed:"+e.getMessage());
            }
            Thread.sleep(500);
        }
    }

    private int failureThresholdCount; // failures to open the circuit
    private long resetTimeoutMillis; // time to wait before trying half-open
    private int  failureCount=0;
    private State state = State.CLOSED;
    private long lastFailureTime=0;

    public CircuitBreaker(int failureThresholdCount, long resetTimeoutMillis){
        this.failureThresholdCount=failureThresholdCount;
        this.resetTimeoutMillis=resetTimeoutMillis;
    }

    public synchronized  <T> T execute(Callable<T> callable) throws Exception{
            long now = System.currentTimeMillis();
            if(state==State.OPEN){
                if(now-lastFailureTime>resetTimeoutMillis){
                    state=State.HALF_OPEN;
                }else{
                    throw new RuntimeException("Call is blocked. Circuit is in OPEN state");
                }
            }
            try{
                T result = callable.call();
                // Success → reset if in half-open or closed
                if(state==State.HALF_OPEN || state==State.CLOSED){
                    reset();
                }
                return result;
            }catch (Exception e){
                failureCount++;
                lastFailureTime=System.currentTimeMillis();
                if(failureCount>=failureThresholdCount){
                    openCircuit();
                }
                throw e;
            }
    }

    public void reset(){
        state=State.CLOSED;
        failureCount=0;
    }

    public void openCircuit(){
        state=State.OPEN;
    }

}

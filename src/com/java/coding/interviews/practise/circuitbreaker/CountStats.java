package com.java.coding.interviews.practise.circuitbreaker;

public class CountStats {
    public int callCount=0;
    public int failureCount=0;
    public int slowCount=0;
    public float failureRate=-1;
    public float slowCallRate=-1;

    public void calculateRates(){
        if(callCount==0){
            failureCount=-1;
            slowCount=-1;
            return;
        }
        failureRate = (float)failureCount*100/(float)callCount;
        slowCallRate=(float)slowCallRate*100/(float) callCount;
    }
}

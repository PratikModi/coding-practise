package com.java.coding.interviews.practise.arcesium;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ZeroOddEvenProblem {

    public static void main(String[] args) {
        Semaphore zeroSemaphore = new Semaphore(1);
        Semaphore oddSemaphore = new Semaphore(0);
        Semaphore evenSemaphore = new Semaphore(0);
        AtomicInteger isIdd = new AtomicInteger(1);
        int limit=50;
        Zero zero = new Zero(zeroSemaphore,oddSemaphore,evenSemaphore,limit,isIdd);
        Odd odd = new Odd(zeroSemaphore,oddSemaphore,evenSemaphore,limit,isIdd);
        Even even = new Even(zeroSemaphore,oddSemaphore,evenSemaphore,limit,isIdd);
        Thread t1 = new Thread(zero);
        Thread t2 = new Thread(odd);
        Thread t3 = new Thread(even);
        t1.start();
        t2.start();
        t3.start();
    }

}

class Zero implements Runnable{

    private Semaphore zeroSemaphore;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;
    private int limit;
    private AtomicInteger isOdd;

    public Zero(Semaphore zeroSemaphore, Semaphore oddSemaphore, Semaphore evenSemaphore, int limit, AtomicInteger isOdd) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.limit = limit;
        this.isOdd = isOdd;
    }

    @Override
    public void run() {
        try {
            for (int i = 2; i < limit; i++) {
                zeroSemaphore.acquire();
                System.out.print(0);
                if(isOdd.get()==1){
                    oddSemaphore.release(1);
                }else{
                    evenSemaphore.release();
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}

class Odd implements Runnable{
    private Semaphore zeroSemaphore;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;
    private int limit;
    private AtomicInteger isOdd;

    public Odd(Semaphore zeroSemaphore, Semaphore oddSemaphore, Semaphore evenSemaphore, int limit, AtomicInteger isOdd) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.limit = limit;
        this.isOdd = isOdd;
    }

    @Override
    public void run() {
        try{
            for (int i=1;i<limit;i+=2){
                oddSemaphore.acquire();
                System.out.print(i);
                isOdd.set(0);
                zeroSemaphore.release();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Even implements Runnable{

    private Semaphore zeroSemaphore;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;
    private int limit;
    private AtomicInteger isOdd;

    public Even(Semaphore zeroSemaphore, Semaphore oddSemaphore, Semaphore evenSemaphore, int limit, AtomicInteger isOdd) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.limit = limit;
        this.isOdd = isOdd;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<limit;i+=2) {
                evenSemaphore.acquire();
                System.out.print(i);
                isOdd.set(1);
                zeroSemaphore.release();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
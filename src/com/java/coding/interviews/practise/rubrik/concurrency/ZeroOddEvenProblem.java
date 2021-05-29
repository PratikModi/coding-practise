package com.java.coding.interviews.practise.rubrik.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506...
 * where the length of the series must be 2n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd().
 * "0102" is the correct output.
 * Example 2:
 *
 * Input: n = 5
 * Output: "0102030405"
 */
public class ZeroOddEvenProblem {

    public static void main(String[] args) {
        Semaphore zeroSemaphore = new Semaphore(1);
        Semaphore oddSemaphore = new Semaphore(0);
        Semaphore evenSemaphore = new Semaphore(0);
        AtomicReference<StringBuilder> SB = new AtomicReference<StringBuilder>();
        SB.set(new StringBuilder());
        AtomicInteger isOdd = new AtomicInteger(1);
        Zero zero = new Zero(zeroSemaphore,oddSemaphore,evenSemaphore,isOdd,5,SB);
        Odd odd = new Odd(zeroSemaphore,oddSemaphore,evenSemaphore,isOdd,5,SB);
        Even even = new Even(zeroSemaphore,oddSemaphore,evenSemaphore,isOdd,5,SB);
        Thread t1 = new Thread(zero);
        t1.start();
        Thread t2 = new Thread(odd);
        t2.start();
        Thread t3 = new Thread(even);
        t3.start();
        System.out.println("==="+SB.get().toString());


    }

}

class Zero implements Runnable{

    Semaphore zeroSemaphore;
    Semaphore oddSemaphore;
    Semaphore evenSemaphore;
    Integer bound;
    AtomicInteger isOdd;
    AtomicReference<StringBuilder> SB;

    public Zero(Semaphore zeroSemaphore,Semaphore oddSemaphore, Semaphore evenSemaphore,AtomicInteger isOdd,int bound, AtomicReference<StringBuilder> SB) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.bound = bound;
        this.isOdd = isOdd;
        this.SB=SB;
    }

    @Override
    public void run() {
        try {
                for(int i=0;i<bound;i++) {
                    zeroSemaphore.acquire();
                    //System.out.println("ZERO");
                    SB.get().append(0);
                    System.out.print(0);
                    if(isOdd.get()==1) {
                        oddSemaphore.release(1);
                    }else{
                        evenSemaphore.release();
                    }
                }

        }catch(InterruptedException IE){
            IE.printStackTrace();
        }

    }
}

class Odd implements Runnable{

    Integer bound;
    Semaphore zeroSemaphore;
    Semaphore oddSemaphore;
    Semaphore evenSemaphore;
    AtomicInteger isOdd;
    AtomicReference<StringBuilder> SB;

    public Odd(Semaphore zeroSemaphore,Semaphore oddSemaphore, Semaphore evenSemaphore,AtomicInteger isOdd,int bound,AtomicReference<StringBuilder> SB) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.bound = bound;
        this.isOdd = isOdd;
        this.SB=SB;
    }

    @Override
    public void run() {
        try{
                for(int i=1;i<=bound;i+=2){
                    //System.out.println(oddSemaphore.availablePermits());
                    oddSemaphore.acquire();
                    System.out.print(i);
                    SB.get().append(i);
                    isOdd.set(0);
                    zeroSemaphore.release();
                }

        }catch (InterruptedException IE){
            IE.printStackTrace();
        }

    }
}

class Even implements Runnable{

    Integer bound;
    Semaphore zeroSemaphore;
    Semaphore oddSemaphore;
    Semaphore evenSemaphore;
    AtomicInteger isOdd;
    AtomicReference<StringBuilder> SB;

    public Even(Semaphore zeroSemaphore,Semaphore oddSemaphore, Semaphore evenSemaphore,AtomicInteger isOdd,int bound,AtomicReference<StringBuilder> SB) {
        this.zeroSemaphore = zeroSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.bound = bound;
        this.isOdd = isOdd;
        this.SB=SB;
    }

    @Override
    public void run() {
        try{
                for(int i=2;i<=bound;i+=2){
                    evenSemaphore.acquire();
                    System.out.print(i);
                    SB.get().append(i);
                    isOdd.set(1);
                    zeroSemaphore.release();
                }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }

    }
}

package com.java.coding.interviews.practise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Pratik1 on 25-04-2020.
 */
public class OddEvenThread {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(1);
        Object lock = new Object();
        EvenThread evenThread = new EvenThread(counter,lock);
        OddThread oddThread = new OddThread(counter,lock);
        //evenThread.start();
        //oddThread.start();

        AtomicInteger counter2 = new AtomicInteger(1);
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

        Thread oThread = new Thread(new Odd(counter2,oddSemaphore,evenSemaphore));
        Thread eThread = new Thread(new Even(counter2,oddSemaphore,evenSemaphore));
        oThread.start();
        eThread.start();

    }

}

class OddThread extends Thread{
    private AtomicInteger counter;
    private Object lock;

    OddThread(AtomicInteger counter, Object lock){
        this.counter=counter;
        this.lock=lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while(true) {
                if (counter.get() % 2 == 0) {
                    try {
                        lock.wait();
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }else{
                    System.out.println("Odd:"+counter.get());
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    counter.incrementAndGet();
                    lock.notifyAll();
                }
            }
        }
    }
}


class EvenThread extends Thread{
    private AtomicInteger counter;
    private Object lock;

    EvenThread(AtomicInteger counter, Object lock){
        this.counter=counter;
        this.lock=lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while(true) {
                if (counter.get() % 2 != 0) {
                    try {
                        lock.wait();
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }else{
                    System.out.println("Even:"+counter.get());
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    counter.incrementAndGet();
                    lock.notifyAll();
                }
            }
        }
    }
}

class Odd implements Runnable{

    private AtomicInteger counter;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;

    public Odd(AtomicInteger counter, Semaphore oddSemaphore, Semaphore evenSemaphore) {
        this.counter = counter;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
    }

    @Override
    public void run() {
        try {
            while (counter.get()<100) {
                oddSemaphore.acquire();
                System.out.println("ODD:" + counter.get());
                Thread.sleep(1000);
                counter.getAndIncrement();
                evenSemaphore.release();
            }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }


    }
}

class Even implements Runnable{

    private AtomicInteger counter;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;

    public Even(AtomicInteger counter, Semaphore oddSemaphore, Semaphore evenSemaphore) {
        this.counter = counter;
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
    }

    @Override
    public void run() {
        try {
            while (counter.get()<100) {
                evenSemaphore.acquire();
                System.out.println("EVEN:" + counter.get());
                Thread.sleep(1000);
                counter.getAndIncrement();
                oddSemaphore.release();
            }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }

    }
}

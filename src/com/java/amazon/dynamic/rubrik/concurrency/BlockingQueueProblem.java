package com.java.amazon.dynamic.rubrik.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Implement a thread-safe bounded blocking queue that has the following methods:
 *
 * BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
 * void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
 * int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
 * int size() Returns the number of elements currently in the queue.
 * Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.
 *
 * Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * 1
 * 1
 * ["BoundedBlockingQueue","enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"]
 * [[2],[1],[],[],[0],[2],[3],[4],[]]
 *
 * Output:
 * [1,0,2,2]
 *
 * Explanation:
 * Number of producer threads = 1
 * Number of consumer threads = 1
 */
public class BlockingQueueProblem {
    public static void main(String[] args) {
        System.out.println(6%3);
    }
}

class BlockingQueue<T>{
    public Queue<T> BQ;
    public int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.BQ = new LinkedList<>();
    }

    public void enqueue(T element){
        if(element==null)
            throw new NullPointerException();
        try {
            synchronized (BQ) {
                if (BQ.size() >= capacity)
                    BQ.wait();
                BQ.add(element);
                BQ.notifyAll();
            }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
    }

    public T dequeue(){
        T element=null;
        try{
            synchronized (BQ){
                if(BQ.size()==0)
                    BQ.wait();
                element=BQ.poll();
                BQ.notifyAll();
            }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
        return element;
    }

    public int size(){
        return BQ.size();
    }

}

class BlockingQueueSemaphore<T>{
    Queue<T> BQ;
    Semaphore enqueue;
    Semaphore dequeue;
    int capacity;

    public BlockingQueueSemaphore(int capacity) {
        this.capacity = capacity;
        enqueue=new Semaphore(capacity);
        dequeue=new Semaphore(0);
    }

    public void enqueue(T element){
        if(element==null)
            throw new NullPointerException();
        try {
            enqueue.acquire();
            BQ.add(element);
            dequeue.release();
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
    }

    public T dequeue(){
        T element=null;
        try{
            dequeue.acquire();
            element=BQ.poll();
            enqueue.release();
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
        return element;
    }
}

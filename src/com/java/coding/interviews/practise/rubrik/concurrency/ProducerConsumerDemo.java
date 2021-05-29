package com.java.coding.interviews.practise.rubrik.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        BlockingQueue<Message> QUEUE = new ArrayBlockingQueue(10);
        Producer producer = new Producer(QUEUE);
        Consumer consumer = new Consumer(QUEUE);
        Thread T1 = new Thread(producer);
        Thread T2 = new Thread(consumer);
        T1.start();
        T2.start();
    }

}

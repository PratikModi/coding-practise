package com.java.coding.interviews.practise.rubrik.concurrency;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    public BlockingQueue<Message> QUEUE;

    public Consumer(BlockingQueue<Message> QUEUE) {
        this.QUEUE = QUEUE;
    }

    @Override
    public void run() {
        Message message;
        try {
            while(!((message=QUEUE.take()).getMessage().equals("EXIT"))) {
                Thread.sleep(1000);
                System.out.println("CONSUMED..." + message);
            }
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
    }
}


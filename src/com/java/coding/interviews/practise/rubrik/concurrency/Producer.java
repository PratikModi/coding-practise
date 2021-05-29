package com.java.coding.interviews.practise.rubrik.concurrency;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    public BlockingQueue<Message> QUEUE;

    public Producer(BlockingQueue<Message> QUEUE) {
        this.QUEUE = QUEUE;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            Message message = new Message("No. "+i);
            try {
                Thread.sleep(1000);
                QUEUE.put(message);
                System.out.println("PRODUCED..."+message);
            }catch (InterruptedException IE){
                IE.printStackTrace();
            }
        }
        Message message = new Message("EXIT");
        try{
            QUEUE.put(message);
        }catch (InterruptedException IE){
            IE.printStackTrace();
        }
    }
}

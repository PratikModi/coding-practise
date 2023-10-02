package com.java.coding.interviews.practise.queue.subscriber.impl;

import com.java.coding.interviews.practise.queue.model.Message;
import com.java.coding.interviews.practise.queue.subscriber.ISubscriber;

import java.util.concurrent.TimeUnit;

public class DemoSubscriber implements ISubscriber {

    private String id;

    public DemoSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void consumeMessage(Message message) throws InterruptedException {
        System.out.println("Subscriber: "+id+" started consuming message:"+message);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Subscriber: "+id+" ended consuming message:"+message);
    }
}

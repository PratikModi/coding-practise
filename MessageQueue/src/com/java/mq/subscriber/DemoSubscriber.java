package com.java.mq.subscriber;

import com.java.mq.interfaces.ISubscriber;
import com.java.mq.model.Message;

public class DemoSubscriber implements ISubscriber {
    private final String id;
    private final int sleepTimeInMillis;

    public DemoSubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consumeMessage(Message message) throws InterruptedException {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getContent());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getContent());
    }
}

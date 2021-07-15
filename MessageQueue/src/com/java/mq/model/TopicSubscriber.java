package com.java.mq.model;

import com.java.mq.interfaces.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;
//Wrapper on top of Subscriber to handle the offset and handle the scenario of reset
public class TopicSubscriber {
    private final AtomicInteger offset;
    private final ISubscriber subscriber;

    public TopicSubscriber(ISubscriber subscriber) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }
}

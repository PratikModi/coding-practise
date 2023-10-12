package com.java.coding.interviews.practise.mq.model;

import com.java.coding.interviews.practise.mq.subscriber.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

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

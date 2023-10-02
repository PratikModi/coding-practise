package com.java.coding.interviews.practise.queue.subscriber;

import com.java.coding.interviews.practise.queue.model.Message;

public interface ISubscriber {
    String getId();
    void consumeMessage(Message message) throws InterruptedException;
}

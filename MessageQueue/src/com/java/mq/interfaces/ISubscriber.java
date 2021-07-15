package com.java.mq.interfaces;

import com.java.mq.model.Message;

public interface ISubscriber {
    String getId();
    void consumeMessage(Message message) throws InterruptedException;
}

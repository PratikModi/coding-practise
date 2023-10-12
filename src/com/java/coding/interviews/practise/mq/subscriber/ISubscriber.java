package com.java.coding.interviews.practise.mq.subscriber;

import com.java.coding.interviews.practise.mq.model.Message;

public interface ISubscriber {
    void consumeMessage(Message message);
    String getId();
}

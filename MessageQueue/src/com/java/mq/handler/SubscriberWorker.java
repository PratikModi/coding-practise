package com.java.mq.handler;

import com.java.mq.model.Message;
import com.java.mq.model.Topic;
import com.java.mq.model.TopicSubscriber;

public class SubscriberWorker implements Runnable{

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;
    //Worked to achieve parallelism in publishing the messages
    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        try {
            synchronized (topicSubscriber) {
                int currOffset = topicSubscriber.getOffset().get();
                if (currOffset >= topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicSubscriber.getSubscriber().consumeMessage(message);
                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                topicSubscriber.getOffset().compareAndSet(currOffset,currOffset+1);
            }
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}

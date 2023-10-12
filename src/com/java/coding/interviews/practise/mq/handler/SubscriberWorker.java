package com.java.coding.interviews.practise.mq.handler;

import com.java.coding.interviews.practise.mq.model.Message;
import com.java.coding.interviews.practise.mq.model.Topic;
import com.java.coding.interviews.practise.mq.model.TopicSubscriber;

public class SubscriberWorker implements Runnable{

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        try{
            synchronized (topicSubscriber){
                var currentOffset = topicSubscriber.getOffset().get();
                if(currentOffset>=topic.getMessages().size()){
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currentOffset);
                topicSubscriber.getSubscriber().consumeMessage(message);
                topicSubscriber.getOffset().compareAndSet(currentOffset,currentOffset+1);
            }
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public synchronized void wakeUpIfNeeded(){
        synchronized (topicSubscriber){
            topicSubscriber.notify();
        }
    }
}

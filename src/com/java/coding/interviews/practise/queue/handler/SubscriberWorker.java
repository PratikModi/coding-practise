package com.java.coding.interviews.practise.queue.handler;

import com.java.coding.interviews.practise.queue.model.Message;
import com.java.coding.interviews.practise.queue.model.Topic;
import com.java.coding.interviews.practise.queue.subscriber.TopicSubscriber;

public class SubscriberWorker implements Runnable{

    private Topic topic;
    private TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }
    @Override
    public void run() {
        try{
            synchronized (topicSubscriber){
                int currentOffset = topicSubscriber.getOffset().get();
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

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}

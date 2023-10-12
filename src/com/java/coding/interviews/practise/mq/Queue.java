package com.java.coding.interviews.practise.mq;

import com.java.coding.interviews.practise.mq.handler.TopicHandler;
import com.java.coding.interviews.practise.mq.model.Message;
import com.java.coding.interviews.practise.mq.model.Topic;
import com.java.coding.interviews.practise.mq.model.TopicSubscriber;
import com.java.coding.interviews.practise.mq.subscriber.ISubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private Map<String, TopicHandler> topicHandlers;

    public Queue() {
        this.topicHandlers=new HashMap<>();
    }

    public Topic createTopic(final String topicName){
        String topicId = UUID.randomUUID().toString();
        Topic newTopic = new Topic(topicId,topicName);
        TopicHandler topicHandler = new TopicHandler(newTopic);
        topicHandlers.put(topicId,topicHandler);
        return newTopic;
    }

    public void subscribe(Topic topic, ISubscriber subscriber){
        topic.addSubscriber(new TopicSubscriber(subscriber));
    }

    public void publish(Topic topic, Message message){
        topic.addMessage(message);
        new Thread(()->topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    public void reset(Topic topic, ISubscriber subscriber, int newOffset){
        for(var topicSubscriber : topic.getTopicSubscribers()){
            if(topicSubscriber.getSubscriber().equals(subscriber)){
                topicSubscriber.getOffset().set(newOffset);
                new Thread(()->topicHandlers.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
            }
        }
    }
}

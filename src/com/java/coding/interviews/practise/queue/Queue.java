package com.java.coding.interviews.practise.queue;

import com.java.coding.interviews.practise.queue.handler.TopicHandler;
import com.java.coding.interviews.practise.queue.model.Message;
import com.java.coding.interviews.practise.queue.model.Topic;
import com.java.coding.interviews.practise.queue.subscriber.ISubscriber;
import com.java.coding.interviews.practise.queue.subscriber.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private Map<String, TopicHandler> topicHandlers;
    public Queue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(final String topicName){
        String topicId = UUID.randomUUID().toString();
        Topic newTopic = new Topic(topicId,topicName);
        TopicHandler topicHandler = new TopicHandler(newTopic);
        topicHandlers.put(newTopic.getTopicId(), topicHandler);
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
        for(var topicSubscriber : topic.getSubscribers()){
            if(topicSubscriber.getSubscriber().equals(subscriber)){
                topicSubscriber.getOffset().set(newOffset);
                new Thread(()->topicHandlers.get(topic.getTopicId()).startSubscriber(topicSubscriber)).start();
            }
        }
    }
}

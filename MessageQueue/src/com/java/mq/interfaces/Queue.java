package com.java.mq.interfaces;

import com.java.mq.handler.TopicHandler;
import com.java.mq.model.Message;
import com.java.mq.model.Topic;
import com.java.mq.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private Map<String, TopicHandler> topicHandlers;

    public Queue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(final String topicName){
        Topic newTopic = new Topic(UUID.randomUUID().toString(),topicName);
        TopicHandler topicHandler = new TopicHandler(newTopic);
        topicHandlers.put(newTopic.getTopicId(),topicHandler);
        System.out.println("Topic Created:--"+newTopic.getTopicName());
        return newTopic;
    }

    public void subscribe(final Topic topic, final ISubscriber subscriber){
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(final Topic topic, final Message message){
        topic.addMessage(message);
        //not to block main thread
        new Thread(()->topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset(final Topic topic, final ISubscriber subscriber, final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicHandlers.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}

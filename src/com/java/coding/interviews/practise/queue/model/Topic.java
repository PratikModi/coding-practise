package com.java.coding.interviews.practise.queue.model;

import com.java.coding.interviews.practise.queue.subscriber.ISubscriber;
import com.java.coding.interviews.practise.queue.subscriber.TopicSubscriber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

    private String topicId;
    private String topicName;
    private List<Message> messages;
    private List<TopicSubscriber> subscribers;

    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getTopicId() {
        return topicId;
    }
    public String getTopicName() {
        return topicName;
    }
    public synchronized void addMessage(Message message){
        messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber){
        this.subscribers.add(subscriber);
    }
    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
    public List<TopicSubscriber> getSubscribers() {
        return Collections.unmodifiableList(subscribers);
    }
}

package com.java.coding.interviews.practise.mq.model;

import com.java.coding.interviews.practise.mq.subscriber.ISubscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Topic {

    private String topicId;
    private String topicName;
    private List<Message> messages;
    private List<TopicSubscriber> topicSubscribers;

    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.topicSubscribers = new ArrayList<>();
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<TopicSubscriber> getTopicSubscribers() {
        return Collections.unmodifiableList(topicSubscribers);
    }

    public synchronized void addMessage(Message message){
        this.messages.add(message);
    }

    public void addSubscriber(TopicSubscriber topicSubscriber){
        this.topicSubscribers.add(topicSubscriber);
    }
}

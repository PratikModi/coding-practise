package com.java.mq.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {
    private final String topicId;
    private final String topicName;
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

    public synchronized void addMessage(final Message message){
        messages.add(message);
    }

    public void addSubscriber(final TopicSubscriber topicSubscriber){
        topicSubscribers.add(topicSubscriber);
    }
   //TO DO:-- return immutable list
    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
    //TO DO:-- return immutable list
    public List<TopicSubscriber> getTopicSubscribers() {
        return Collections.unmodifiableList(topicSubscribers);
    }
}

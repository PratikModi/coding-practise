package com.java.mq.handler;

import com.java.mq.model.Topic;
import com.java.mq.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String,SubscriberWorker> subscriberWorkers;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish(){
        for(TopicSubscriber subscriber : topic.getTopicSubscribers()){
            startSubscriberWorker(subscriber);
        }
    }

    public void startSubscriberWorker(final TopicSubscriber topicSubscriber){
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if(!subscriberWorkers.containsKey(subscriberId)){
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic,topicSubscriber);
            subscriberWorkers.put(subscriberId,subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }

}

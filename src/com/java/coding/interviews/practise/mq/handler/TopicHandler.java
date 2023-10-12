package com.java.coding.interviews.practise.mq.handler;

import com.java.coding.interviews.practise.mq.model.Topic;
import com.java.coding.interviews.practise.mq.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberworkers;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberworkers = new HashMap<>();
    }

    public void publish(){
        for(var worker : topic.getTopicSubscribers()){

        }
    }

    public void startSubscriberWorker(final TopicSubscriber topicSubscriber){
        final var subscriberId = topicSubscriber.getSubscriber().getId();
        if(!subscriberworkers.containsKey(subscriberId)){
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic,topicSubscriber);
            subscriberworkers.put(subscriberId,subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberworkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}

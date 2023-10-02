package com.java.coding.interviews.practise.queue;

import com.java.coding.interviews.practise.queue.model.Message;
import com.java.coding.interviews.practise.queue.model.Topic;
import com.java.coding.interviews.practise.queue.subscriber.ISubscriber;
import com.java.coding.interviews.practise.queue.subscriber.impl.DemoSubscriber;

public class InMemoryQueueProblem {

    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        Topic T1 = queue.createTopic("Topic A");
        Topic T2 = queue.createTopic("Topic B");

        ISubscriber subscriber1 = new DemoSubscriber("S1");
        ISubscriber subscriber2 = new DemoSubscriber("S2");
        ISubscriber subscriber3 = new DemoSubscriber("S3");

        queue.subscribe(T1,subscriber1);
        queue.subscribe(T1,subscriber2);

        queue.subscribe(T2, subscriber3);

        queue.publish(T1, new Message("m1"));
        queue.publish(T1, new Message("m2"));

        queue.publish(T2, new Message("m3"));

        Thread.sleep(15000);
        queue.publish(T2, new Message("m4"));
        queue.publish(T1, new Message("m5"));
    }

}

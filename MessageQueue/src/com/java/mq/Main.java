package com.java.mq;

import com.java.mq.interfaces.ISubscriber;
import com.java.mq.interfaces.Queue;
import com.java.mq.model.Message;
import com.java.mq.model.Topic;
import com.java.mq.subscriber.DemoSubscriber;

public class Main {

    public static void main(String[] args) throws Exception {
        Queue queue = new Queue();
        Topic T1 = queue.createTopic("Topic A");
        Topic T2 = queue.createTopic("Topic B");

        ISubscriber subscriber1 = new DemoSubscriber("S1", 1000);
        ISubscriber subscriber2 = new DemoSubscriber("S2", 2000);
        ISubscriber subscriber3 = new DemoSubscriber("S3", 2500);

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

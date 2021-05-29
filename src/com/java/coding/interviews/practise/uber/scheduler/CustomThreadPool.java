package com.java.coding.interviews.practise.uber.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class CustomThreadPool {

    private int capacity;
    private BlockingQueue<Runnable> queue;
    private Workers[] workers;
    public CustomThreadPool(int capacity){
        this.capacity = capacity;
        queue = new DelayQueue();
        workers = new Workers[this.capacity];
        for(int i=0;i<capacity;i++){
            workers[i]=new Workers();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class Workers extends Thread {
        public void run() {
            Task task=null;
            while (true) {
                synchronized (queue){
                    while(queue.isEmpty()){
                        try {
                            queue.wait();
                        }catch (InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }
                    try {
                        task = (Task) queue.take();
                    }catch (Exception ie){
                        ie.printStackTrace();
                    }
                }
                try{
                    task.run();
                    if (task.isReRunnable()) {
                        execute(new Task(task.getScheduleTime().plusSeconds(task.getDelay()), true, task.getDelay()));
                    }

                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown(){
        for(int i=0;i<capacity;i++){
            workers[i]=null;
        }
    }
}

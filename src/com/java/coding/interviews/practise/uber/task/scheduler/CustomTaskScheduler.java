package com.java.coding.interviews.practise.uber.task.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Problem
 * Implement an InMemory Task scheduler Library that supports these functionalities:
 * Submit a task and a time at which the task should be executed. --> schedule(task, time)
 * Schedule a task at a fixed interval --> scheduleAtFixedInterval(task, interval) - interval is in seconds
 * The first instance will trigger it immediately and the next execution would start after interval seconds of completion of the preceding execution.
 * If a task has an interval of 10 seconds and submitted at 2:00 pm then
 * It will be executed at 2:00 pm
 * Once the execution is completed + 10 seconds(interval) it will trigger the next execution and so on.
 * Expectations
 * The number of worker threads should be configurable and manage them effectively.
 * Code/Design should be modular and follow design patterns.
 * Don’t use any external/internal libs that provide the same functionality and core APIs should be used.
 */
public class CustomTaskScheduler {

    private PriorityQueue<ScheduleTask> taskQueue;
    private ThreadPoolExecutor customThreadPool;
    private Lock lock = new ReentrantLock();
    private Condition taskAddedCondition = lock.newCondition();

    public CustomTaskScheduler(int workerThreadPoolSize) {
        taskQueue = new PriorityQueue<>(Comparator.comparingLong(ScheduleTask::getScheduleTime));
        customThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerThreadPoolSize);
    }

    public void start(){
        long timeToSleep=0;
        while (true){
            lock.lock();
            try{
                while(taskQueue.isEmpty()){
                    taskAddedCondition.await();
                }
                while(!taskQueue.isEmpty()){
                    timeToSleep = taskQueue.peek().getScheduleTime()-System.currentTimeMillis();
                    if(timeToSleep<=0){
                        break;
                    }
                    taskAddedCondition.await(timeToSleep, TimeUnit.MILLISECONDS);
                }
                ScheduleTask scheduleTask = taskQueue.poll();
                long newScheduleTime=0;
                switch (scheduleTask.getTaskType()){
                    case ONCE:
                        customThreadPool.submit(scheduleTask.getRunnable());
                        break;
                    case RE_RUNNABLE:
                        newScheduleTime = System.currentTimeMillis()+scheduleTask.getUnit().toMillis(scheduleTask.getPeriod());
                        customThreadPool.submit(scheduleTask.getRunnable());
                        scheduleTask.setScheduleTime(newScheduleTime);
                        taskQueue.add(scheduleTask);
                        break;
                    case RE_RUNNABLE_WITH_COMPLETION_WAIT:
                        Future<?> future = customThreadPool.submit(scheduleTask.getRunnable());
                        future.get();
                        newScheduleTime = System.currentTimeMillis()+scheduleTask.getUnit().toMillis(scheduleTask.getDelay());
                        scheduleTask.setScheduleTime(newScheduleTime);
                        taskQueue.add(scheduleTask);
                        break;
                }
            }catch (Exception e){
                System.out.println("Something Wrong in Start");
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    public void schedule(Runnable command, long delay, TimeUnit unit){
        lock.lock();
        try{
            long scheduleTime = System.currentTimeMillis()+unit.toMillis(delay);
            ScheduleTask scheduleTask = new ScheduleTask(command,scheduleTime,null,null,unit,TaskType.ONCE);
            taskQueue.add(scheduleTask);
            taskAddedCondition.signalAll();
        }catch (Exception e){
            System.out.println("Something Went Wrong..");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */

    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit){
        lock.lock();
        try{
            long scheduleTime = System.currentTimeMillis()+unit.toMillis(initialDelay);
            ScheduleTask scheduleTask = new ScheduleTask(command,scheduleTime,period,null,unit,TaskType.RE_RUNNABLE);
            taskQueue.add(scheduleTask);
            taskAddedCondition.signalAll();
        }catch (Exception e){
            System.out.println("Something Went Wrong..");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void scheduleWithFixedDelay(Runnable command,long initialDelay, long delay, TimeUnit unit){
        lock.lock();
        try{
            long scheduleTime = System.currentTimeMillis()+unit.toMillis(initialDelay);
            ScheduleTask scheduleTask = new ScheduleTask(command,scheduleTime,null,delay,unit,TaskType.RE_RUNNABLE_WITH_COMPLETION_WAIT);
            taskQueue.add(scheduleTask);
            taskAddedCondition.signalAll();
        }catch (Exception e){
            System.out.println("Something Went Wrong..");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static Runnable getRunnableTask(String s) {
        return () -> {
            System.out.println(s +" started at " + System.currentTimeMillis() / 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s +" ended at " + System.currentTimeMillis() / 1000);
        };
    }

    public static void main(String[] args) {
        CustomTaskScheduler customTaskScheduler = new CustomTaskScheduler(10);
        Runnable task1 = getRunnableTask("Task1");
        customTaskScheduler.schedule(task1, 1, TimeUnit.SECONDS);
        Runnable task2 = getRunnableTask("Task2");
        customTaskScheduler.scheduleAtFixedRate(task2,1, 2, TimeUnit.SECONDS);
        Runnable task3 = getRunnableTask("Task3");
        customTaskScheduler.scheduleWithFixedDelay(task3,1,2,TimeUnit.SECONDS);
        Runnable task4 = getRunnableTask("Task4");
        customTaskScheduler.scheduleAtFixedRate(task4,1, 2, TimeUnit.SECONDS);
        customTaskScheduler.start();
    }
}


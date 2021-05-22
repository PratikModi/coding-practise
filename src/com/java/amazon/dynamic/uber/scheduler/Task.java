package com.java.amazon.dynamic.uber.scheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable, Delayed {

    private LocalDateTime scheduleTime;
    private boolean isReRunnable;
    private long delay;

    public Task(LocalDateTime scheduleTime, boolean isReRunnable, long delay) {
        this.scheduleTime = scheduleTime;
        this.isReRunnable = isReRunnable;
        this.delay = delay;
    }

    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public boolean isReRunnable() {
        return isReRunnable;
    }

    public long getDelay() {
        return delay;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing Task:::"+ LocalDateTime.now());
        }catch (Exception ie){
            ie.printStackTrace();
        }
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        long diff = this.getScheduleTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()-System.currentTimeMillis();
        return timeUnit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return this.getScheduleTime().compareTo(((Task)delayed).getScheduleTime());
    }


    @Override
    public String toString() {
        return "Task{" +
                "scheduleTime=" + getScheduleTime() +
                ", isReRunnable=" + isReRunnable +
                ", delay=" + delay +
                '}';
    }
}

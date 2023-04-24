package com.java.coding.interviews.practise.uber.task.scheduler;

import java.util.concurrent.TimeUnit;

public class ScheduleTask {

    private Runnable runnable;
    private Long scheduleTime;
    private Long period;
    private Long delay;
    private TimeUnit unit;
    private TaskType taskType;

    public ScheduleTask(Runnable runnable, Long scheduleTime, Long period, Long delay, TimeUnit unit, TaskType taskType) {
        this.runnable = runnable;
        this.scheduleTime = scheduleTime;
        this.period = period;
        this.delay = delay;
        this.unit = unit;
        this.taskType = taskType;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public Long getScheduleTime() {
        return scheduleTime;
    }

    public Long getPeriod() {
        return period;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Long getDelay() {
        return delay;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}


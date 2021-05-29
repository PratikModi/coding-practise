package com.java.coding.interviews.practise.uber.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SchedulerTest {
    public static void main(String[] args) {

        CustomThreadPool pool = new CustomThreadPool(2);
        for(int i=0;i<1;i++){
            Task task = new Task(LocalDateTime.of(LocalDate.now(), LocalTime.now().plusSeconds((i+1)*10)),true,5);
            pool.execute(task);
        }
    }
}

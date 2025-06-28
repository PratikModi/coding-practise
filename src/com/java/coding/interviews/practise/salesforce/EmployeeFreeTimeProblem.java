package com.java.coding.interviews.practise.salesforce;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeFreeTimeProblem {

    public static String getEarliestMeeting(List<String> events, int k){
        if(events==null || events.size()==0)
            return "-1";
        List<Interval> flatIntervals = new ArrayList<>();
        for(String event : events){
            String[] splits = event.split(" ");
            flatIntervals.add(new Interval(convert(splits[2]),convert(splits[3])));
        }
        flatIntervals.add(new Interval(LocalTime.of(23,59),LocalTime.of(23,59)));
        flatIntervals.sort((a, b) -> a.startTime.compareTo(b.startTime));
        List<Interval> merged = mergeIntervals(flatIntervals);
        Interval previous = new Interval(LocalTime.of(0,0),LocalTime.of(0,0));
        for(var interval : merged){
            if(Duration.between(previous.endTime,interval.startTime).toMinutes()>=k){
                return previous.endTime.plusMinutes(1).format(DateTimeFormatter.ofPattern("HH:mm"));
            }else{
                previous=interval;
            }
        }
        return "-1";
    }



    private static List<Interval> mergeIntervals(List<Interval> intervals){
        List<Interval> merged = new ArrayList<>();
        Interval previous = intervals.get(0);
        merged.add(previous);
        for(int i=1;i<intervals.size();i++){
            Interval current = intervals.get(i);
            if(!previous.endTime.isBefore(current.startTime)){
                previous.endTime = previous.endTime.isAfter(current.endTime)?previous.endTime:current.endTime;
            }else{
                previous=current;
                merged.add(previous);
            }
        }
        return merged;
    }

    private static LocalTime convert(String time){
        return LocalTime.parse(time,DateTimeFormatter.ofPattern("HH:mm"));
    }
}

class Interval{
    LocalTime startTime;
    LocalTime endTime;

    public Interval(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

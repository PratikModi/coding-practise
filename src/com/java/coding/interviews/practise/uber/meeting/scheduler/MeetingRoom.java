package com.java.coding.interviews.practise.uber.meeting.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {

    private MeetingCalendar calendar;
    private List<Meeting> meetings;
    private String name;

    public MeetingRoom(String name) {
        this.calendar = new MeetingCalendar(this);
        this.name = name;
        this.meetings = new ArrayList<>();
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end){
        return calendar.isAvailable(start,end);
    }

    public Meeting scheduleNewMeeting(LocalDateTime start, LocalDateTime end){
        return calendar.scheduleNewMeeting(start,end);
    }

    public MeetingCalendar getCalendar() {
        return calendar;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public String getName() {
        return name;
    }
}

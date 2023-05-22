package com.java.coding.interviews.practise.uber.meeting.scheduler;

import java.time.LocalDateTime;

public class MeetingCalendar {

    private MeetingRoom room;

    public MeetingCalendar(MeetingRoom room) {
        this.room = room;
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end){
        var meetings = room.getMeetings();
        for(Meeting meeting : meetings){
            if(meeting.getEndTime().isAfter(start) || meeting.getStartTime().isBefore(end))
                return false;
        }
        return true;
    }

    public Meeting scheduleNewMeeting(LocalDateTime start, LocalDateTime end){
        Meeting meeting = new Meeting(start,end,room);
        room.getMeetings().add(meeting);
        return meeting;
    }


}

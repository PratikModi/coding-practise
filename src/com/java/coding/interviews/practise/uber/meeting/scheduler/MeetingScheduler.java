package com.java.coding.interviews.practise.uber.meeting.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Design Meeting Scheduler. Here there are n given meeting rooms. Book a meeting in any meeting room at given interval(starting time, end time).
 * Also send notifications to all person who are invited for meeting.
 * You should use calender for tracking date and time. And also history of all the meetings which are booked and meeting room.
 * write an API for client who will give date and time and API should return meeting room with booked scheduled time.
 * client should also query for history of last 20 booked meetings.
 * Is meeting room available? etc
 */
public class MeetingScheduler {
    private static final int MAX_MEETING_HISTORY = 20;

    private List<MeetingRoom> meetingRooms;
    private List<Meeting> history;

    public MeetingScheduler(List<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
        this.history = new ArrayList<>();
    }

    public Meeting book(LocalDateTime start, LocalDateTime end){
        Meeting bookedMeeting = null;
        try{
            for(MeetingRoom room : meetingRooms){
                if(room.isAvailable(start, end)){
                    bookedMeeting =  room.scheduleNewMeeting(start,end);
                    saveToHistory(bookedMeeting);
                    return bookedMeeting;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("No Meeting Rooms Available.....");
    }

    private void saveToHistory(Meeting meeting){
        if(history.size()==MAX_MEETING_HISTORY){
            history.set(0,meeting);
            return;
        }
        history.add(meeting);
    }

    public void sendInvite(Meeting meeting, List<Attendee> attendees){
        meeting.sendInvite(attendees);
    }

}

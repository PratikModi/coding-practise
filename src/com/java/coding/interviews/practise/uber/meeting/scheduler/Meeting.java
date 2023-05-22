package com.java.coding.interviews.practise.uber.meeting.scheduler;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {

    private int meetingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MeetingRoom meetingRoom;
    private List<Attendee> attendees;
    private EmailService emailService;

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, MeetingRoom meetingRoom) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingRoom = meetingRoom;
        this.meetingId=1;//Id Generation
        this.emailService = new EmailService();
    }

    public void sendInvite(List<Attendee> attendees){

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }
}

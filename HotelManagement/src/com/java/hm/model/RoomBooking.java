package com.java.hm.model;

import com.java.hm.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RoomBooking {
    private String reservationNumber;
    private LocalDate startDate;
    private int duration;
    private BookingStatus bookingStatus;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int guestId;
    private Room room;
    private Invoice invoice;
    private List<Notification> notifications;

    public RoomBooking fetchDetail(String reservationNumber){
        return new RoomBooking();
    }


}

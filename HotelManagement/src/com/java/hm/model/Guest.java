package com.java.hm.model;

import java.util.List;

public class Guest extends Person{

    private int totalRoomsCheckedIn;
    public List<RoomBooking> getBookings(){
        return null;
    }
    public boolean createBooking(RoomBooking booking){
        return true;
    }

}

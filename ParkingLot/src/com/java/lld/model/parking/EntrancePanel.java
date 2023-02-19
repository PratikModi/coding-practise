package com.java.lld.model.parking;

import com.java.lld.enums.TicketStatus;

import java.time.LocalDateTime;

public class EntrancePanel {

    private String id;

    public ParkingTicket printTicket(){
        return new ParkingTicket("1", LocalDateTime.now(),null,3d, TicketStatus.ACTIVE);
    }
}

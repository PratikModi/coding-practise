package com.java.lld.model.vehicle;

import com.java.lld.enums.VehicleType;
import com.java.lld.model.parking.ParkingTicket;

public class Van extends Vehicle{
    public Van(String licenseNumber, ParkingTicket parkingTicket) {
        super(licenseNumber, VehicleType.VAN, parkingTicket);
    }
}

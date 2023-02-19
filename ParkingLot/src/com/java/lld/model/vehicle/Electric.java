package com.java.lld.model.vehicle;

import com.java.lld.enums.VehicleType;
import com.java.lld.model.parking.ParkingTicket;

public class Electric extends Vehicle{
    public Electric(String licenseNumber, ParkingTicket parkingTicket) {
        super(licenseNumber, VehicleType.ELECTRIC, parkingTicket);
    }
}

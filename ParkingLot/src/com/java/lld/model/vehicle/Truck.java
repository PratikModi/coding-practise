package com.java.lld.model.vehicle;

import com.java.lld.enums.VehicleType;
import com.java.lld.model.parking.ParkingTicket;

public class Truck extends Vehicle{

    public Truck(String licenseNumber,ParkingTicket parkingTicket) {
        super(licenseNumber, VehicleType.TRUCK, parkingTicket);
    }
}

package com.java.lld.model.vehicle;

import com.java.lld.enums.VehicleType;
import com.java.lld.model.parking.ParkingTicket;

public class Car extends Vehicle{

    public Car(String licenseNumber, ParkingTicket parkingTicket) {
        super(licenseNumber, VehicleType.CAR, parkingTicket);
    }
}

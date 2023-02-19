package com.java.lld.model.parking;

import com.java.lld.enums.ParkingSpotType;

public class ElectricParkingSpot extends ParkingSpot{

    public ElectricParkingSpot() {
        super(ParkingSpotType.ELECTRIC);
    }

    @Override
    public boolean isFree() {
        return isFree();
    }
}

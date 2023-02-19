package com.java.lld.model.parking;

import com.java.lld.enums.ParkingSpotType;
import com.java.lld.model.vehicle.Vehicle;

public abstract class ParkingSpot {
    private String number;
    private ParkingSpotType parkingSpotType;
    private boolean free;
    private Vehicle vehicle;

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract boolean isFree();

    public boolean assignVehicle(Vehicle vehicle){
        this.vehicle=vehicle;
        this.free=false;
        return true;
    }

    public boolean removeVehicle(){
        this.vehicle=null;
        this.free=true;
        return true;
    }
}

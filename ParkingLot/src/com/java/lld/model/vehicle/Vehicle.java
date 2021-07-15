package com.java.lld.model.vehicle;

import com.java.lld.enums.VehicleType;
import com.java.lld.model.parking.ParkingTicket;

import java.util.Objects;

public abstract class Vehicle {

    private String licenseNumber;
    private VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licenseNumber, VehicleType vehicleType, ParkingTicket parkingTicket) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
        this.parkingTicket = parkingTicket;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licenseNumber, vehicle.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }
}

package com.java.lld.model.parking;

import com.java.lld.enums.TicketStatus;
import com.java.lld.enums.VehicleType;
import com.java.lld.model.account.Address;
import com.java.lld.model.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Map;

public class ParkingLot {

    private ParkingLot(){
        maxCompactCount=100;
        maxLargeCount=100;
        maxMotorbikeCount=100;
        maxElectricCount=100;
    }

    private static ParkingLot parkingLot=null;

    private static String name;
    private static Address address;

    public static ParkingLot getInstance(){
        if(parkingLot==null){
            synchronized (ParkingLot.class){
                if(parkingLot==null){
                    parkingLot=new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    private ParkingRate parkingRate;

    private int compactSpotCount;
    private int largeSpotCount;
    private int motorbikeSpotCount;
    private int electricSpotCount;
    private final int maxCompactCount;
    private final int maxLargeCount;
    private final int maxMotorbikeCount;
    private final int maxElectricCount;

    private Map<String, EntrancePanel> entrancePanels;
    private Map<String, ExitPanel> exitPanels;
    private Map<String, ParkingFloor> parkingFloors;

    // all active parking tickets, identified by their ticketNumber
    private Map<String, ParkingTicket> activeTickets;

    // note that the following method is 'synchronized' to allow multiple entrances
    // panels to issue a new parking ticket without interfering with each other
    public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws Exception {//ParkingFullException {
        if (this.isFull(vehicle.getVehicleType())) {
            throw new Exception();//ParkingFullException();
        }
        ParkingTicket ticket = new ParkingTicket("1", LocalDateTime.now(),null,3d, TicketStatus.ACTIVE);
        vehicle.setParkingTicket(ticket);
        //ticket.saveInDB();
        // if the ticket is successfully saved in the database, we can increment the parking spot count
        this.incrementSpotCount(vehicle.getVehicleType());
        this.activeTickets.put(ticket.getTicketNumber(), ticket);
        return ticket;
    }

    public boolean isFull(VehicleType type) {
        // trucks and vans can only be parked in LargeSpot
        if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
            return largeSpotCount >= maxLargeCount;
        }

        // motorbikes can only be parked at motorbike spots
        if (type == VehicleType.MOTORBIKE) {
            return motorbikeSpotCount >= maxMotorbikeCount;
        }

        // cars can be parked at compact or large spots
        if (type == VehicleType.CAR) {
            return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
        }

        // electric car can be parked at compact, large or electric spots
        return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount
                + maxElectricCount);
    }

    // increment the parking spot count based on the vehicle type
    private boolean incrementSpotCount(VehicleType type) {
        if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
            largeSpotCount++;
        } else if (type == VehicleType.MOTORBIKE) {
            motorbikeSpotCount++;
        } else if (type == VehicleType.CAR) {
            if (compactSpotCount < maxCompactCount) {
                compactSpotCount++;
            } else {
                largeSpotCount++;
            }
        } else { // electric car
            if (electricSpotCount < maxElectricCount) {
                electricSpotCount++;
            } else if (compactSpotCount < maxCompactCount) {
                compactSpotCount++;
            } else {
                largeSpotCount++;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (String key : parkingFloors.keySet()) {
            if (!parkingFloors.get(key).isFull()) {
                return false;
            }
        }
        return true;
    }

    public void addParkingFloor(ParkingFloor floor) {
        /* store in database */ }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        /* store in database */ }

    public void addExitPanel(ExitPanel exitPanel) {
        /* store in database */ }
}

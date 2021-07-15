package com.java.lld.model.parking;

import com.java.lld.model.vehicle.Vehicle;

import java.util.Map;

public class ParkingFloor {
    private String name;
    private Map<String, HandicappedParkingSpot> handicappedSpots;
    private Map<String, CompactParkingSpot> compactSpots;
    private Map<String, LargeParkingSpot> largeSpots;
    private Map<String, MotorBikeParkingSpot> motorbikeSpots;
    private Map<String, ElectricParkingSpot> electricSpots;
    private ParkingDisplayBoard parkingDisplayBoard;
    private Map<String, CustomerInfoPortal> customerInfoPortalMap;

    public ParkingFloor(String name) {
        this.name = name;
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getParkingSpotType()) {
            case HANDICAPPED:
                handicappedSpots.put(spot.getNumber(), (HandicappedParkingSpot) spot);
                break;
            case COMPACT:
                compactSpots.put(spot.getNumber(), (CompactParkingSpot)spot);
                break;
            case LARGE:
                largeSpots.put(spot.getNumber(), (LargeParkingSpot) spot);
                break;
            case MOTORBIKE:
                motorbikeSpots.put(spot.getNumber(), (MotorBikeParkingSpot) spot);
                break;
            case ELECTRIC:
                electricSpots.put(spot.getNumber(), (ElectricParkingSpot) spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicle(vehicle);
        switch (spot.getParkingSpotType()) {
            case HANDICAPPED:
                updateDisplayBoardForHandicapped(spot);
                break;
            case COMPACT:
                updateDisplayBoardForCompact(spot);
                break;
            case LARGE:
                //updateDisplayBoardForLarge(spot);
                break;
            case MOTORBIKE:
                //updateDisplayBoardForMotorbike(spot);
                break;
            case ELECTRIC:
                //updateDisplayBoardForElectric(spot);
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
        if (this.parkingDisplayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
            // find another free handicapped parking and assign to displayBoard
            for (String key : handicappedSpots.keySet()) {
                if (handicappedSpots.get(key).isFree()) {
                    this.parkingDisplayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                }
            }
            this.parkingDisplayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForCompact(ParkingSpot spot) {
        if (this.parkingDisplayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
            // find another free compact parking and assign to displayBoard
            for (String key : compactSpots.keySet()) {
                if (compactSpots.get(key).isFree()) {
                    this.parkingDisplayBoard.setCompactFreeSpot(compactSpots.get(key));
                }
            }
            this.parkingDisplayBoard.showEmptySpotNumber();
        }
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
        switch (spot.getParkingSpotType()) {
            case HANDICAPPED:
                //freeHandicappedSpotCount++;
                break;
            case COMPACT:
                //freeCompactSpotCount++;
                break;
            case LARGE:
                //freeLargeSpotCount++;
                break;
            case MOTORBIKE:
                //freeMotorbikeSpotCount++;
                break;
            case ELECTRIC:
                //freeElectricSpotCount++;
                break;
            default:
                System.out.println("Wrong parking spot type!");
        }
    }

    public boolean isFull(){
        return false;
    }

}

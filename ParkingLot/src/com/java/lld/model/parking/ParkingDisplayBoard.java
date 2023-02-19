package com.java.lld.model.parking;

public class ParkingDisplayBoard {
    private String id;
    private HandicappedParkingSpot handicappedFreeSpot;
    private CompactParkingSpot compactFreeSpot;
    private LargeParkingSpot largeFreeSpot;
    private MotorBikeParkingSpot motorbikeFreeSpot;
    private ElectricParkingSpot electricFreeSpot;

    public ParkingDisplayBoard(String id) {
        this.id = id;
    }

    public HandicappedParkingSpot getHandicappedFreeSpot() {
        return handicappedFreeSpot;
    }

    public CompactParkingSpot getCompactFreeSpot() {
        return compactFreeSpot;
    }

    public LargeParkingSpot getLargeFreeSpot() {
        return largeFreeSpot;
    }

    public MotorBikeParkingSpot getMotorbikeFreeSpot() {
        return motorbikeFreeSpot;
    }

    public ElectricParkingSpot getElectricFreeSpot() {
        return electricFreeSpot;
    }

    public void setHandicappedFreeSpot(HandicappedParkingSpot handicappedFreeSpot) {
        this.handicappedFreeSpot = handicappedFreeSpot;
    }

    public void setCompactFreeSpot(CompactParkingSpot compactFreeSpot) {
        this.compactFreeSpot = compactFreeSpot;
    }

    public void setLargeFreeSpot(LargeParkingSpot largeFreeSpot) {
        this.largeFreeSpot = largeFreeSpot;
    }

    public void setMotorbikeFreeSpot(MotorBikeParkingSpot motorbikeFreeSpot) {
        this.motorbikeFreeSpot = motorbikeFreeSpot;
    }

    public void setElectricFreeSpot(ElectricParkingSpot electricFreeSpot) {
        this.electricFreeSpot = electricFreeSpot;
    }

    public void showEmptySpotNumber(){
        String message = "";
        if(handicappedFreeSpot.isFree()){
            message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
        } else {
            message += "Handicapped is full";
        }
        message += System.lineSeparator();

        if(compactFreeSpot.isFree()){
            message += "Free Compact: " + compactFreeSpot.getNumber();
        } else {
            message += "Compact is full";
        }
        message += System.lineSeparator();

        if(largeFreeSpot.isFree()){
            message += "Free Large: " + largeFreeSpot.getNumber();
        } else {
            message += "Large is full";
        }
        message += System.lineSeparator();

        if(motorbikeFreeSpot.isFree()){
            message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
        } else {
            message += "Motorbike is full";
        }
        message += System.lineSeparator();

        if(electricFreeSpot.isFree()){
            message += "Free Electric: " + electricFreeSpot.getNumber();
        } else {
            message += "Electric is full";
        }

        show(message);
    }

    private void show(String message){

    }

}

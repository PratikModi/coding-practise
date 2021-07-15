package com.java.lld.model.parking;

import com.java.lld.enums.TicketStatus;

import java.time.LocalDateTime;

public class ParkingTicket {

    private String ticketNumber;
    private LocalDateTime issuedAt;
    private LocalDateTime payedAt;
    private double amount;
    private TicketStatus ticketStatus;

    public ParkingTicket(String ticketNumber, LocalDateTime issuedAt, LocalDateTime payedAt, double amount, TicketStatus ticketStatus) {
        this.ticketNumber = ticketNumber;
        this.issuedAt = issuedAt;
        this.payedAt = payedAt;
        this.amount = amount;
        this.ticketStatus = ticketStatus;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalDateTime payedAt) {
        this.payedAt = payedAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", issuedAt=" + issuedAt +
                ", payedAt=" + payedAt +
                ", amount=" + amount +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}

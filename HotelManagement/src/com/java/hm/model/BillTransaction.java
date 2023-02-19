package com.java.hm.model;

import com.java.hm.enums.PaymentStatus;

import java.time.LocalDateTime;

public abstract class BillTransaction {

    private LocalDateTime creationDate;
    private double amount;
    private PaymentStatus paymentStatus;

    public abstract boolean initiateTransaction();

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

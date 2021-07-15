package com.java.hm.model;

public class PostalNotification extends Notification {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean sendNotification(){
        return true;
    }
}

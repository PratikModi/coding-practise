package com.java.hm.model;

public class EmailNotification extends Notification{
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean sendNotification(){
        return true;
    }
}

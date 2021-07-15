package com.java.hm.model;

import java.time.LocalDateTime;

public class RoomKey {

    private String keyId;
    private String barcode;
    private LocalDateTime issuedAt;
    private boolean isActive;
    private boolean isMaster;

    public boolean assignRoom(Room room){
        return true;
    }

    public boolean isActive(){
        return true;
    }
}

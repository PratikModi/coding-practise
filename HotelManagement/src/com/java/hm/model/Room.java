package com.java.hm.model;

import com.java.hm.enums.RoomStyle;
import com.java.hm.service.Search;

import java.time.LocalDate;
import java.util.List;

public class Room implements Search {

    @Override
    public List<Room> search(RoomStyle style, LocalDate startDate, int duration) {
        return null;
    }
}

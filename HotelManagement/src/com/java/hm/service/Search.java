package com.java.hm.service;

import com.java.hm.enums.RoomStyle;
import com.java.hm.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface Search {
    List<Room> search(RoomStyle style, LocalDate startDate, int duration);
}

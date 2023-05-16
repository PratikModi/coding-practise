package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The question was as following:
 *
 * Given an infinite stream of events and events are like:
 *
 * user1 city1 BOOKED timestamp1
 * user2 city1 CANCELLED timestamp2
 * user3 city1 BOOKED timestamp3
 * user4 city1 BOOKED timestamp4
 * user1 city2 ENDED timestamp5
 *
 * On these events we have to build a system where query can be anything like
 *
 * How many cabs are BOOKED between timestamp1 to timestamp4.
 * How many cabse are BOOKED in city1 and ended in city2 between timestamp1 to timestamp4.
 * Expectation of this round was to build some generic datastructure that can support query of anytype.
 *
 * Expectation1- How we are going to ingest these events, since its a DS/ALGO round so a in memory datastructure was expected.
 * Expectation2- Support any types of query.
 */

public class CarRentalQueryProblem {
    private TreeMap<Long,List<Pair2>> bookedMap;
    private TreeMap<Long,List<Pair2>> endedMap;

    public CarRentalQueryProblem() {
        this.bookedMap = new TreeMap<>();
        this.endedMap = new TreeMap<>();
    }

    public void processStream(Booking booking){
        if(booking.status.equals(Status.CANCELLED))
            return;
        if(booking.status.equals(Status.BOOKED)){
            bookedMap.putIfAbsent(booking.timestamp,new ArrayList<>());
            bookedMap.get(booking.timestamp).add(new Pair2(booking.user, booking.city));
        }else{
            endedMap.putIfAbsent(booking.timestamp,new ArrayList<>());
            endedMap.get(booking.timestamp).add(new Pair2(booking.user, booking.city));
        }
    }

    public int totalBookedCab(long timestamp1, long timestamp2){
         return bookedMap.subMap(timestamp1,timestamp2).size();
    }
}



class Booking{
    String user;
    String city;
    Status status;
    long timestamp;

    public Booking(String user, String city, Status status, long timestamp) {
        this.user = user;
        this.city = city;
        this.status = status;
        this.timestamp = timestamp;
    }
}

enum Status{
    BOOKED,ENDED,CANCELLED;
}

class Pair2{
    String user;
    String city;

    public Pair2(String user, String city) {
        this.user = user;
        this.city = city;
    }
}
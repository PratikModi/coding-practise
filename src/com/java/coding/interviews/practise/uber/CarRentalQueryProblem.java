package com.java.coding.interviews.practise.uber;

import java.util.*;
import java.util.stream.Collectors;

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
 * How many cabs are BOOKED in city1 and ended in city2 between timestamp1 to timestamp4.
 * Expectation of this round was to build some generic data structure that can support query of any type.
 *
 * Expectation1- How we are going to ingest these events, since its a DS/ALGO round so a in memory datastructure was expected.
 * Expectation2- Support any types of query.
 */

public class CarRentalQueryProblem {
    private TreeMap<Long,Set<Pair2>> bookedMap;
    private TreeMap<Long,Set<Pair2>> endedMap;

    public CarRentalQueryProblem() {
        this.bookedMap = new TreeMap<>();
        this.endedMap = new TreeMap<>();
    }

    public static void main(String[] args) {
        CarRentalQueryProblem query = new CarRentalQueryProblem();
        Booking B1 = new Booking("U1","C1",Status.BOOKED,1);
        query.processStream(B1);
        System.out.println(query.totalBookedCab(1,2));
        System.out.println(query.totalBookedEndedCabsInCity(1,2,"C1","C2"));
        Booking B2 = new Booking("U2","C1",Status.CANCELLED,2);
        query.processStream(B2);
        Booking B3 = new Booking("U3","C1",Status.BOOKED,3);
        query.processStream(B3);
        System.out.println(query.totalBookedCab(1,2));
        System.out.println(query.totalBookedCab(1,4));
        System.out.println(query.totalBookedEndedCabsInCity(1,4,"C1","C2"));
        Booking B4 = new Booking("U4","C1",Status.BOOKED,4);
        query.processStream(B4);
        System.out.println(query.totalBookedCab(1,4));
        System.out.println(query.totalBookedCab(1,5));
        System.out.println(query.totalBookedEndedCabsInCity(1,5,"C1","C2"));
        Booking B5 = new Booking("U1","C2",Status.ENDED,5);
        query.processStream(B5);
        System.out.println(query.totalBookedCab(1,5));
        System.out.println(query.totalBookedCab(1,6));
        System.out.println(query.totalBookedEndedCabsInCity(1,6,"C1","C2"));
    }

    public void processStream(Booking booking){
        if(booking.status.equals(Status.CANCELLED))
            return;
        if(booking.status.equals(Status.BOOKED)){
            bookedMap.putIfAbsent(booking.timestamp,new HashSet<>());
            bookedMap.get(booking.timestamp).add(new Pair2(booking.user, booking.city));
        }else{
            endedMap.putIfAbsent(booking.timestamp,new HashSet<>());
            endedMap.get(booking.timestamp).add(new Pair2(booking.user, booking.city));
        }
    }

    public int totalBookedCab(long timestamp1, long timestamp2){
         return bookedMap.subMap(timestamp1,timestamp2).values().size();
    }

    public int totalBookedEndedCabsInCity(long timestamp1, long timestamp2, String source, String dest){
        List<Pair2> bookedCab = new ArrayList<>();
        bookedCab.addAll(bookedMap.subMap(timestamp1,timestamp2).values().stream().reduce((a,b)->a).orElse(Collections.EMPTY_SET));
        List<Pair2> endedCab = new ArrayList<>();
        endedCab.addAll(endedMap.subMap(timestamp1,timestamp2).values().stream().reduce((a,b)->a).orElse(Collections.EMPTY_SET));
        return bookedCab.stream().filter(e->e.city.equals(source))
                .filter(e->endedCab.contains(new Pair2(e.user,dest)))
                .collect(Collectors.toSet()).size();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(user, booking.user) && Objects.equals(city, booking.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, city);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair2 pair2 = (Pair2) o;
        return Objects.equals(user, pair2.user) && Objects.equals(city, pair2.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, city);
    }

    @Override
    public String toString() {
        return "Pair2{" +
                "user='" + user + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
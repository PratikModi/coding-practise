package com.java.coding.interviews.practise.booking;

import java.util.*;
import java.util.stream.Collectors;

public class HotelReservationProblem {

    public static List<Room> findRooms(Map<Integer,List<Room>> hotelRooms, int checkIn, int checkOut, int minAvailability, List<String> features){
        List<Room> result = new ArrayList<>();
        if(hotelRooms==null || hotelRooms.isEmpty())
            return result;
        find(hotelRooms,checkIn,checkOut,minAvailability,features,result);
        return result;
    }

    private static void find(Map<Integer,List<Room>> hotelRooms, int checkIn, int checkOut, int minAvailability, List<String> features, List<Room> result){
        Queue<List<Room>> validRooms = new LinkedList<>();
        while(checkIn<checkOut){
            validRooms.add(hotelRooms.get(checkIn++).stream().filter(e->e.availability>=minAvailability && isFeaturePreset(e.features,features)).collect(Collectors.toList()));
        }
        while(validRooms.size()>1){
            List<Room> room1 = validRooms.poll();
            List<Room> room2 = validRooms.poll();
            merge(room1,room2,result);
        }

    }

    public static void main(String[] args) {
        HashMap<Integer,List<Room>> map = new HashMap<>();

        List<Room> rooms176 = new ArrayList<>();
        rooms176.add(new Room(120, 5,List.of("breakfast", "refundable")));
        map.put(176, rooms176);

        List<Room> rooms177 = new ArrayList<>();
        rooms177.add(new Room(130,1,List.of("breakfast")));
        rooms177.add(new Room(140,3, List.of("breakfast", "refundable", "wifi")));
        map.put(177, rooms177);

        List<Room> rooms178 = new ArrayList<>();
        rooms178.add(new Room(130,2, List.of("breakfast")));
        rooms178.add(new Room(140,10,List.of("breakfast", "refundable", "wifi")));
        map.put(178, rooms178);

        var result = findRooms(map,176,178,1,List.of("breakfast"));
        System.out.println(result);

    }



    private static boolean isFeaturePreset(List<String> roomFeatures, List<String> askedFeatures){
        boolean isPreset=true;
        Set<String> roomFeatureSet = new HashSet<>(roomFeatures);
        Set<String> askedFeatureSet = new HashSet<>(askedFeatures);
        for(String feature : askedFeatureSet){
            if(!roomFeatureSet.contains(feature)){
                isPreset=false;
            }
        }
        return isPreset;
    }

    private static List<String> commonFeatures(List<String> room1Features, List<String> room2Features){
        Set<String> room1FeatureSet = new HashSet<>(room1Features);
        Set<String> room2FeatureSet = new HashSet<>(room2Features);
        List<String> common = new ArrayList<>();
        for(String feature : room1FeatureSet){
            if(room2FeatureSet.contains(feature)){
                common.add(feature);
            }
        }
        return common;
    }

    private static void merge(List<Room> room1, List<Room> room2, List<Room> merged){

        for(Room r1 : room1){
            for(Room r2 : room2){
                merged.add(new Room(r1.price+r2.price,Math.min(r1.availability,r2.availability),commonFeatures(r1.features,r2.features)));
            }
        }
    }


}

class Room{
    int price;
    int availability;
    List<String> features;

    public Room(int price, int availability, List<String> features) {
        this.price = price;
        this.features = features;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Room{" +
                "price=" + price +
                ", availability=" + availability +
                ", features=" + features +
                '}';
    }
}

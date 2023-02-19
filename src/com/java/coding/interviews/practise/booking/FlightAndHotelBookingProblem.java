package com.java.coding.interviews.practise.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of flights between destinations. we have to start from a given source and come back to source with in a given budget and time. lets say
 *
 * Amsterdam -> Paris on August 10, Price - 300 euros
 * London -> Paris on August 15, Price - 410 euros
 * Paris -> London on August 13, Price - 300 euros
 * London -> Amsterdam on August 17, Price - 400 euros
 * Paris -> Amsterdam on August 21, Price - 500 euros
 *
 * Hotel cost per night:
 * Amsterdam - 400 euros
 * Paris - 500 euros
 * London - 300 euros
 *
 * Find the longest itinerary possible with in a given budget 5000 and time
 *
 * Lets say we start from amsterdam on 10th Aug. now we can go paris. from paris we have two options.
 * either we can go to london again or back to amsterdam.
 *
 * Option 1:
 *
 * Amsterdam -> Paris {flight} 300
 * Paris -> London {flight} 300
 * London -> Paris {flight} 410
 * Paris -> Amsterdam {flight} 400
 *
 * Total Flight cost : 1410
 *
 * Hotel paris : 3 days {10 to 13} 1500
 * Hotel London : 2 days {13 to 15} 600
 * Hotel Paris : 6 days {15 to 21} 3000
 *
 * Total cost is going above 5000 so user cant use this path
 *
 * Option 2:
 *
 * Amsterdam -> Paris {flight} 300
 * Paris -> London {flight} 300
 * London -> Amsterdam {flight} 400
 *
 * Total Flight code : 1000
 *
 * Hotel paris : 3 days {10 to 13} 1500
 * Hotel London : 4 days {13 to 17} 1200
 *
 * Total cost = 1500 + 1200 + 1000 = 3700
 *
 * So this it in is possible in the given budget and date . So output will be Amsterdam -> Paris -> London -> Amsterdam
 */
public class FlightAndHotelBookingProblem {

    static Map<String, List<Map<String, List<Integer>>>> flights = new HashMap<>();
    static Map<String, Integer> hotelCost = new HashMap<>();
    static Integer budget;
    static Integer deadLine;
    static List<String> result = new ArrayList<>();
    public static List<String> findItinerary(String source, Integer currentDay){
        dfs(source,source,0,currentDay,new ArrayList<>());
        return result;
    }

    private static void dfs(String source, String destination, Integer currentCost, Integer currentDay, List<String> itinerary){
        //System.out.println(source +"-->"+destination+"-->"+currentCost+"-->"+currentDay+"-->"+itinerary);
        if(currentCost>budget)
            return;
        if(currentDay>deadLine)
            return;
        itinerary.add(source);
        if(source.equals(destination) && itinerary.size()>1){
            if(itinerary.size() > result.size()){
                result = new ArrayList<>(itinerary);
            }
            itinerary.remove(itinerary.size()-1);
            return;
        }
        var airLines = flights.get(source);
        for(var flight : airLines) {
            for (var neighbor : flight.entrySet()) {
                int departureDay = neighbor.getValue().get(0);
                if (departureDay < currentDay) {
                    continue;
                }
                int airLineCost = neighbor.getValue().get(1);
                int hCost = hotelCost.get(source) * (departureDay - currentDay);
                //System.out.println((departureDay - currentDay));
                dfs(neighbor.getKey(), destination, currentCost + hCost + airLineCost, departureDay, itinerary);
            }
        }
        itinerary.remove(itinerary.size()-1);
    }

    public static void main(String[] args) {
        flights.put("Amsterdam",List.of(Map.of("Paris",List.of(10,300))));
        flights.put("London",List.of(Map.of("Paris",List.of(15,410)),Map.of("Amsterdam",List.of(17,400))));
        flights.put("Paris",List.of(Map.of("London",List.of(13,300)),Map.of("Amsterdam",List.of(21,500))));

        hotelCost.put("Amsterdam",400);
        hotelCost.put("Paris",500);
        hotelCost.put("London",300);

        budget=5000;
        deadLine=21;

        findItinerary("Amsterdam",10);
        System.out.println(result);
    }
}

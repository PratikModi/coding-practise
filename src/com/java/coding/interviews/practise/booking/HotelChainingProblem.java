package com.java.coding.interviews.practise.booking;

import java.util.*;

/**
 * There is list of hotels and they are given in below format:
 * { hotel id, parent hotel id, no. of hotel}
 * multi level hotel hierarchy exits in the system.
 * example:
 * [ { 3,0,14}
 * {0, null, 10}
 * {4,0,44}
 * {6, null, 7}
 * {10, 6, 13}
 * {7, 6, 17}
 * {2, null, 2}
 * {14, 2, 9}
 * {25, 14, 10}
 * {12, 2, 10}
 * {13, 0, 1}
 * {14, 2, 9}
 * ]
 * <p>
 * Here null represent the top level of the hotel. We want to know top k hotel chains.
 * o/p: if k =2:
 * {(0,69), (2,56)}
 */
public class HotelChainingProblem {

    static Map<Integer, Integer> parentHotelRelationShip = new HashMap<>();
    static Map<Integer, Integer> hotelChain = new HashMap<>();
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        return b[1] - a[1];
    });

    public static List<List<Integer>> findTopHotelChains(List<HotelChain> hotelChains, int k) {
        List<List<Integer>> result = new ArrayList<>();
        buildParentRelationShip(hotelChains, parentHotelRelationShip);
        for (HotelChain chain : hotelChains) {
            if (chain.parentHotelId != -1) {
                //hotelChain.put(chain.hotelId, hotelChain.getOrDefault(chain.hotelId, 0) + chain.noOfHotels);
                hotelChain.put(chain.parentHotelId, hotelChain.getOrDefault(chain.parentHotelId, 0) + chain.noOfHotels);
            } else {
                hotelChain.put(chain.hotelId, hotelChain.getOrDefault(chain.hotelId, 0) + chain.noOfHotels);
            }
        }
        hotelChain.entrySet().stream().forEach(entry->{
            pq.add(new int[] {entry.getKey(), entry.getValue()});
        });
        while(!pq.isEmpty() && k-->0){
            int[] p = pq.poll();
            result.add(List.of(p[0],p[1]));
        }
        return result;
    }

    private static void buildParentRelationShip(List<HotelChain> hotelChains, Map<Integer, Integer> parentHotelRelationShip) {
        for (HotelChain chain : hotelChains) {
            if (chain.parentHotelId == -1) {
                parentHotelRelationShip.put(chain.hotelId, chain.hotelId);
            } else {
                parentHotelRelationShip.putIfAbsent(chain.hotelId, chain.parentHotelId);
            }
        }
    }

    public static void main(String[] args) {
        List<HotelChain> hotelChains = List.of(
                new HotelChain(3, 0, 14),
                new HotelChain(0, -1, 10),
                new HotelChain(4, 0, 44),
                new HotelChain(6, -1, 7),
                new HotelChain(10, 6, 13),
                new HotelChain(7, 6, 17),
                new HotelChain(2, -1, 2),
                new HotelChain(14, 2, 9),
                new HotelChain(25, 14, 10),
                new HotelChain(12, 2, 10),
                new HotelChain(13, 0, 1),
                new HotelChain(14, 2, 9)
        );

        var result = findTopHotelChains(hotelChains,2);
        System.out.println(result);
    }

}

class HotelChain {
    int hotelId;
    int parentHotelId;
    int noOfHotels;

    public HotelChain(int hotelId, int parentHotelId, int noOfHotels) {
        this.hotelId = hotelId;
        this.parentHotelId = parentHotelId;
        this.noOfHotels = noOfHotels;
    }
}

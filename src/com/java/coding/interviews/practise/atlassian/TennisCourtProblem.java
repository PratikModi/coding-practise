package com.java.coding.interviews.practise.atlassian;

import java.util.*;

/**
 * ✅ Problem Statement
 *
 * You manage a tennis club with multiple courts. Players book courts for specific time intervals (e.g., start and end times). Given a list of bookings, you need to:
 * 	•	Assign courts efficiently so that no two overlapping bookings are on the same court.
 * 	•	Return either:
 * 	•	The minimum number of courts required to accommodate all bookings, OR
 * 	•	The court assignment for each booking.
 *
 * 	✅ Approach
 *
 * 1. Minimum Courts Required
 * 	•	Sort bookings by start time
 * 	•	Use a min-heap (priority queue) to track court availability by end time
 * 	•	For each booking:
 * 	•	If the earliest ending court is free (endTime <= start), reuse it
 * 	•	Else, allocate a new court
 * 	•	Heap size = number of courts needed
 *
 * Time Complexity:
 * 	•	Sorting: O(n log n)
 * 	•	Heap operations: O(n log n)
 * Total: O(n log n)
 */
public class TennisCourtProblem {
    public static void main(String[] args) {
        int[][] bookings = {{1, 3}, {2, 4}, {3, 5}};
        System.out.println(minimumCourtRequired(bookings));
        List<Booking> bookingList = Arrays.asList(
                new Booking(1, 3),
                new Booking(2, 4),
                new Booking(3, 5),
                new Booking(6, 7)
        );

        Map<Integer, List<Booking>> result = assignCourts(bookingList);
        System.out.println("Court Assignments:");
        for (Map.Entry<Integer, List<Booking>> entry : result.entrySet()) {
            System.out.print("Court " + entry.getKey() + ": ");
            for (Booking b : entry.getValue()) {
                System.out.print("(" + b.startTime + "," + b.endTime + ") ");
            }
            System.out.println();
        }

        List<Court> courtList = Arrays.asList(
                new Court(1, 1, 8),
                new Court(2, 2, 6)
        );
            scheduleCourts(courtList);
        List<Booking> bookingsList = Arrays.asList(
                new Booking(1, 3),  // Should go to Court 1
                new Booking(2, 4),  // Should go to Court 2
                new Booking(3, 5),  // Can reuse Court 1
                new Booking(5, 7),  // Court 1 still fits
                new Booking(6, 8)   // Court 1 OK, but Court 2 can't (availability ends at 6)
        );

        for (Booking b : bookingsList) {
            System.out.println(assignCourts(b));
        }
    }

    static class Booking{
        int startTime;
        int endTime;

        Booking(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    static class Court{
        int courtId;
        int startTime;
        int endTime;
        int nextAvailableTime;
        Court(int courtId, int endTime) {
            this.courtId = courtId;
            this.endTime = endTime;
        }

        Court(int courtId, int availableStartTime, int availableEndTime) {
            this.courtId = courtId;
            this.startTime = availableStartTime;
            this.endTime = availableEndTime;
            this.nextAvailableTime = availableStartTime;
        }
    }
    public static int minimumCourtRequired(int[][] bookings){
        if(bookings == null || bookings.length == 0) return 0;
        Arrays.sort(bookings,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] booking : bookings){
            if(!pq.isEmpty() && pq.peek()<=booking[0]){
                pq.poll();
            }
            pq.add(booking[1]);
        }
        return pq.size();
    }

    /**
     * ✅ 1. Court Assignment with Court IDs
     *
     * Here we not only find minimum courts, but also assign each booking to a specific court.
     */

    public static Map<Integer, List<Booking>> assignCourts(List<Booking>  bookings){
        if(bookings == null || bookings.size() == 0) return null;
        Map<Integer,List<Booking>> bookingMap = new HashMap<>();
        bookings.sort(Comparator.comparingInt(a->a.startTime));
        PriorityQueue<Court> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a.endTime));
        int courtId=0;
        for(Booking booking : bookings){
            if(!minHeap.isEmpty() && minHeap.peek().endTime<=booking.startTime){
                Court court = minHeap.poll();
                court.endTime = booking.endTime;
                bookingMap.get(court.courtId).add(booking);
                minHeap.offer(court);
            }else{
                courtId++;
                Court court = new Court(courtId, booking.endTime);
                bookingMap.put(court.courtId,new ArrayList<>(List.of(booking)));
                minHeap.offer(court);
            }
        }
        return bookingMap;
    }

    /**
     * ✅ Problem Variant
     * 	•	You manage multiple tennis courts.
     * 	•	Each court has a fixed availability window (e.g., Court 1: 1–8, Court 2: 2–6).
     * 	•	Bookings come with start and end times.
     * 	•	You must assign a booking only to a court that is available during that time and not already occupied.
     * 	•	If no court fits, reject the booking.
     *
     * 	✅ Approach
     * 	•	Maintain a min-heap per court or a single priority queue sorted by earliest endTime.
     * 	•	Extra check: Ensure that the booking time falls inside the court’s availability range.
     * 	•	If no valid court, reject.
     */

    static PriorityQueue<Court> minHeap;

    public static void scheduleCourts(List<Court> courts){
        minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a.nextAvailableTime));
        minHeap.addAll(courts);
    }

    public static String assignCourts(Booking booking){
        List<Court> temp = new ArrayList<>();
        while(!minHeap.isEmpty()){
            Court court = minHeap.poll();
            if(booking.startTime>=court.startTime && booking.endTime<=court.endTime){
                if(court.nextAvailableTime<=booking.startTime){
                    court.nextAvailableTime = booking.endTime;
                    minHeap.offer(court);
                    return "Booking (" + booking.startTime + "," + booking.endTime + ") -> Court " + court.courtId;
                }
            }
            temp.add(court);
        }
        // Put all courts back
        minHeap.addAll(temp);
        return "Booking (" + booking.startTime + "," + booking.endTime + ") -> Rejected (No Available Court)";
    }

}

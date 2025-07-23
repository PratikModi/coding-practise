package com.java.coding.interviews.practise.atlassian;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Problem Statement
 * 	•	A cinema operates between 10:00 AM and 11:00 PM (13 hours = 780 minutes).
 * 	•	Each movie has a duration (e.g., 120 minutes).
 * 	•	There is a cleaning/buffer time (e.g., 15 minutes) required after each movie.
 * 	•	Task: Determine if a new movie can be added to the schedule without overlapping existing movies,
 * 	     considering cleaning times.
 * Optionally, return all possible time slots where the new movie can fit.
 */

//Total Time Complexity
//
//O(n og n) + O(n) + O(1) = O(n og n)
public class CinemaTheatreProblem {
    static class Interval{
        LocalTime start;
        LocalTime end;
        Interval(LocalTime start, LocalTime end){
            this.start = start;
            this.end = end;
        }
    }

    public static List<String> findAvailableSlots(List<Interval> movieSlots, int movieDuration, int cleaningTime){
        List<String> availableSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(10,00);
        LocalTime endTime  = LocalTime.of(23,00);
        movieSlots.sort(Comparator.comparing(i->i.start));
        LocalTime currentTime = startTime;
        for(Interval movie : movieSlots){
            if(movie.start.isAfter(currentTime.plusMinutes(movieDuration+cleaningTime))){
                    LocalTime slotEnd = currentTime.plusMinutes(movieDuration);
                    availableSlots.add(currentTime+" - "+ slotEnd);
            }
            currentTime = movie.end;
        }
        //After Last movie
        if(!currentTime.plusMinutes(movieDuration).isAfter(endTime)){
            availableSlots.add(currentTime +" - "+ currentTime.plusMinutes(movieDuration));
        }
        return availableSlots;
    }
    public static void main(String[] args) {
        List<Interval> existing = Arrays.asList(
                new Interval(LocalTime.of(12, 0), LocalTime.of(14, 15)),
                new Interval(LocalTime.of(16, 15), LocalTime.of(18, 30))
        );

        int movieDuration = 120;  // 2 hours
        int cleaningTime = 15;    // 15 min cleaning


        /*List<String> slots = findAvailableSlots(existing, newMovieDuration, cleaningTime);
        System.out.println("Available slots for new movie:");
        slots.forEach(System.out::println);
        List<String> slots2 = findAvailableSlots2(existing,newMovieDuration,cleaningTime);
        System.out.println("Available slots for new movie:");
        slots2.forEach(System.out::println);*/
        List<String> slots3 = findAllPossibleSlots(existing,movieDuration,cleaningTime);
        System.out.println("Available slots for new movie:");
        slots3.forEach(System.out::println);
    }

    public static List<String> findAvailableSlots2(List<Interval> existing, int movieDuration, int cleaningTime){
        List<String> availableSlots = new ArrayList();
        LocalTime open = LocalTime.of(10,00);
        LocalTime close = LocalTime.of(23,00);
        existing.sort(Comparator.comparing(i->i.start));

        //Check Before First movie
        if(!existing.isEmpty()){
            LocalTime firstMovieStart = existing.get(0).start;
            if(open.plusMinutes(movieDuration+cleaningTime).isBefore(firstMovieStart)){
                availableSlots.add(open + " - "+ open.plusMinutes(movieDuration));
            }
        }else{
            // No existing movies
            if (open.plusMinutes(movieDuration + cleaningTime).isBefore(close)) {
                availableSlots.add(open + " - " + open.plusMinutes(movieDuration));
            }
            return availableSlots;
        }

        // Check gaps between movies
        for (int i = 0; i < existing.size() - 1; i++) {
            LocalTime currentEnd = existing.get(i).end;  // end already includes cleaning time
            LocalTime nextStart = existing.get(i + 1).start;
            if (!currentEnd.plusMinutes(movieDuration + cleaningTime).isAfter(nextStart)) {
                availableSlots.add(currentEnd + " - " + currentEnd.plusMinutes(movieDuration));
            }
        }
        // Check after last movie
        LocalTime lastEnd = existing.get(existing.size() - 1).end;
        if (!lastEnd.plusMinutes(movieDuration + cleaningTime).isAfter(close)) {
            availableSlots.add(lastEnd + " - " + lastEnd.plusMinutes(movieDuration));
        }

        return availableSlots;
    }

    public static List<String> findAllPossibleSlots(List<Interval> existing, int movieDuration, int cleaningTime) {
        List<String> availableSlots = new ArrayList<>();
        LocalTime startDay = LocalTime.of(10, 0);  // 10 AM
        LocalTime endDay = LocalTime.of(23, 0);    // 11 PM

        // Sort movies by start time
        existing.sort(Comparator.comparing(i -> i.start));

        // 1. Slots before the first movie
        if (!existing.isEmpty()) {
            addConsecutiveSlots(startDay, existing.get(0).start, movieDuration, cleaningTime, availableSlots);
        } else {
            // No existing movies, fill the entire day
            addConsecutiveSlots(startDay, endDay, movieDuration, cleaningTime, availableSlots);
            return availableSlots;
        }

        // 2. Slots between movies
        for (int i = 0; i < existing.size() - 1; i++) {
            LocalTime gapStart = existing.get(i).end;
            LocalTime gapEnd = existing.get(i + 1).start;
            addConsecutiveSlots(gapStart, gapEnd, movieDuration, cleaningTime, availableSlots);
        }

        // 3. Slots after the last movie
        addConsecutiveSlots(existing.get(existing.size() - 1).end, endDay, movieDuration, cleaningTime, availableSlots);

        return availableSlots;
    }

    private static void addConsecutiveSlots(LocalTime gapStart, LocalTime gapEnd,
                                            int movieDuration, int cleaningTime, List<String> slots) {
        LocalTime current = gapStart;
        while (!current.plusMinutes(movieDuration + cleaningTime).isAfter(gapEnd)) {
            slots.add(current + " - " + current.plusMinutes(movieDuration));
            current = current.plusMinutes(movieDuration + cleaningTime); // move to next possible slot
        }
    }
}


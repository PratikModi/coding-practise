package com.java.coding.interviews.practise.arcesium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * N meetings in one room
 *
 * Problem Statement: There is one meeting room in a firm. You are given two arrays, start and end each of size N.For an index ‘i’,
 * start[i] denotes the starting time of the ith meeting while end[i]  will denote the ending time of the ith meeting.
 * Find the maximum number of meetings that can be accommodated if only one meeting can happen in the room at a  particular time.
 * Print the order in which these meetings will be performed.
 *
 * Example:
 * Input:  N = 6,  start[] = {1,3,0,5,8,5}, end[] =  {2,4,5,7,9,9}
 * Output: 1 2 4 5
 * Explanation: See the figure for a better understanding.
 */
public class MeetingInSingleRoomProblem {

    public static void main(String[] args) {
        int[] start = new int[]{1,3,0,5,8,5};
        int[] end = new int[]{2,4,5,7,9,9};
        System.out.println(findMaxMeeting(start,end));
    }

    public static int findMaxMeeting(int[] start, int[] end){
        List<Meeting> meetings = new ArrayList<>();
        for(int i=0;i<start.length;i++){
            meetings.add(new Meeting(start[i],end[i],i+1));
        }
        Collections.sort(meetings,(a,b)->a.endTime== b.endTime?a.position-b.position:a.endTime-b.endTime);
        List<Integer> positions = new ArrayList<>();
        positions.add(meetings.get(0).position);
        int previous = meetings.get(0).endTime;
        for(int i=1;i<meetings.size();i++){
            Meeting current = meetings.get(i);
            if(previous<= current.startTime){
                positions.add(meetings.get(i).position);
                previous=meetings.get(i).endTime;
            }
        }
        positions.stream().forEach(System.out::println);
        return positions.size();
    }


}
class Meeting{
    int startTime;
    int endTime;
    int position;

    public Meeting(int startTime, int endTime, int position) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.position = position;
    }

}

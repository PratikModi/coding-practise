package com.java.coding.interviews.practise.uber;

import java.util.List;

/**
 * you are given a list of intervals
 * [1, 3], [4, 7]
 * each interval means cars parked in it.
 *
 * For example [1, 3] means cars parked from 1 to 2, and 2 to 3 like this..
 * now when a new interval [6, 8] comes, then 6,7 is already occupied by interval 4, 7 so it has space left only in 7,8 which is a single slot..
 * so for every interval you have to say how many slots are available for it.. at first tell me did you understand the question and then solve it optimally
 * the answer is [2, 3, 1]
 *
 * 2 for interval [1, 3] which includes 1 to 2 and 2 to 3
 * 3 for interval [4, 7] which includes 4 to 5, 5 to 6 and 6 to 7
 * 1 for interval 6 to 8 because although there is 6 to 7 and 7 to 8, but then 6 to 7 is already occupied sp only 7 to 8 is left. so answer is 1 for it
 *
 * For interval [1, 3], it calculates 2 available slots (covering [1, 2] and [2, 3]).
 * For interval [4, 7], it also calculates 2 available slots (covering [4, 5], [5, 6], and [6, 7]). This seems to be an error since it should be 3 slots.
 * For interval [6, 8], it correctly identifies 1 available slot (covering [7, 8]), as [6, 7] is already occupied by [4, 7].
 */
public class ParkingSlotProblem {

    public static int findEmptyParkingSlots(List<int[]> slots, int[] newSlot){
        if(slots.isEmpty()) {
            if (newSlot != null || newSlot.length != 0)
                return newSlot[1] - newSlot[0];
        }
        if(newSlot==null || newSlot.length==0)
            return 0;
        int i=0;
        int result = 0;
        int N = slots.size();
        while(i<N && slots.get(i)[1] < newSlot[0]){
            i++;
        }
        if(i==N){
            return newSlot[1]-newSlot[0];
        }
        while(i<N && slots.get(i)[0] <= newSlot[1]){
            int slot=slots.get(i)[1] - slots.get(i)[0];
            int newStart = Math.min(slots.get(i)[0], newSlot[0]);
            int newEnd = Math.max(slots.get(i)[1], newSlot[1]);
            result+=Math.abs((newEnd-newStart)-slot);
            i++;
        }
        return result;
    }


    public static void main(String[] args) {
        List<int[]> parkingSlot = List.of(new int[]{1, 3}, new int[]{4, 5});
        int[] newSlot = new int[]{6, 8};
        System.out.println(findEmptyParkingSlots(parkingSlot,newSlot));

    }



}

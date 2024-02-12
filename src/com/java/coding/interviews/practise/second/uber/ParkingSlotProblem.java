package com.java.coding.interviews.practise.second.uber;

import java.util.List;

public class ParkingSlotProblem {


    public static int findNumSlots(List<int[]> slots, int[] newSlot){
        int i=0;
        int n = slots.size();
        int result=0;
        while(i<n && slots.get(i)[1]<newSlot[0]){
            i++;
        }
        if(i==n){
            return newSlot[1]-newSlot[0];
        }
        while(i<n && slots.get(i)[0]<=newSlot[1]){
            int slot = slots.get(i)[1] - slots.get(i)[0];
            int newStart = Math.min(slots.get(i)[0],newSlot[0]);
            int newEnd = Math.max(slots.get(i)[1],newSlot[1]);
            result+=Math.abs((newEnd-newStart)-slot);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        List<int[]> slots = List.of(new int[]{1,3}, new int[]{4,6});
        int[] newSlot = {5,8};
        System.out.println(findNumSlots(slots,newSlot));
    }

}

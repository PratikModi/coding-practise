package com.java.coding.interviews.practise.second.uber;

import java.util.TreeSet;

public class ExamRoomProblem {
    private int capacity;
    private TreeSet<Integer> seats;

    public ExamRoomProblem(int capacity) {
        this.capacity = capacity;
        this.seats = new TreeSet<>();
    }

    public int seat(){
        Integer prev = null;
        int seatNumber = 0;
        if(seats.size()>0){
            int distance = seats.first();
            for(int seat : seats){
                if(prev!=null){
                    int d = (seat-prev)/2;
                    if(d>distance){
                        distance=d;
                        seatNumber = prev+distance;
                    }
                }
                prev =seat;
            }
            if(distance<capacity-1-seats.last()){
                seatNumber = capacity-1;
            }
        }
        seats.add(seatNumber);
        return seatNumber;
    }

    public void remove(int p){
        seats.remove(p);
    }

    public static void main(String[] args) {
        ExamRoomProblem test = new ExamRoomProblem(10);
        System.out.println(test.seat());
        System.out.println(test.seat());
        System.out.println(test.seat());
        System.out.println(test.seat());
        test.remove(4);
        System.out.println(test.seat());
    }
}

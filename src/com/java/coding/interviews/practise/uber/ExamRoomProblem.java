package com.java.coding.interviews.practise.uber;

import java.util.TreeSet;

/**
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats,
 * they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.
 *
 * Design a class that simulates the mentioned exam room.
 *
 * Implement the ExamRoom class:
 *
 * ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 * int seat() Returns the label of the seat at which the next student will set.
 * void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.
 *
 *
 * Example 1:
 *
 * Input
 * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 * [[10], [], [], [], [], [4], []]
 * Output
 * [null, 0, 9, 4, 2, null, 5]
 *
 * Explanation
 * ExamRoom examRoom = new ExamRoom(10);
 * examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
 * examRoom.seat(); // return 9, the student sits at the last seat number 9.
 * examRoom.seat(); // return 4, the student sits at the last seat number 4.
 * examRoom.seat(); // return 2, the student sits at the last seat number 2.
 * examRoom.leave(4);
 * examRoom.seat(); // return 5, the student sits at the last seat number 5.
 */
public class ExamRoomProblem {
    int capacity;
    TreeSet<Integer> seats;

    public ExamRoomProblem(int capacity) {
        this.capacity = capacity;
        this.seats = new TreeSet<>();
    }

    public int seat(){
        Integer prev = null;
        int seatNumber =0;
        if(seats.size()>0){
            int distance = seats.first();
            for(int seat : seats){
                if(prev!=null){
                    int d = (seat-prev)/2;
                    if(d>distance){
                        distance=d;
                        seatNumber= prev+distance;
                    }
                }
                prev=seat;
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

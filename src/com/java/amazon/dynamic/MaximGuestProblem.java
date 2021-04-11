package com.java.amazon.dynamic;

import java.util.Arrays;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class MaximGuestProblem {

    public static void main(String[] args) {
        findMaximumGuest(new int[]{1,2,10,5,5},new int[]{4,5,12,9,12},5);
    }

    public static void findMaximumGuest(int[] arrival,int[] exit, int n){
        int current_guest_in=0;
        int total_guest=0;
        int current_time=0;

        current_guest_in=total_guest=1;
        current_time=arrival[0];
        int i=1;
        int j=0;

        Arrays.sort(arrival);
        Arrays.sort(exit);

        while(i<n&&j<n){
            if(arrival[i] <= exit[j]){
                current_guest_in++;
                if(total_guest<current_guest_in){
                    total_guest=current_guest_in;
                    current_time=arrival[i];
                }
                i++;
            }else{
                current_guest_in--;
                j++;
            }
        }

        System.out.println("Maximum Guest "+total_guest+" at time of "+current_time);
    }

}

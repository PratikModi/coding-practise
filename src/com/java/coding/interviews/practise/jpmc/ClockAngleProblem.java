package com.java.coding.interviews.practise.jpmc;

/**
 * This problem is known as Clock angle problem where we need to find angle between hands of an analog clock at a given time.
 * Examples:
 *
 * Input:
 * h = 12:00
 * m = 30.00
 * Output:
 * 165 degree
 *
 * Input:
 * h = 3.00
 * m = 30.00
 * Output:
 * 75 degree
 */
public class ClockAngleProblem {

    public static void main(String[] args) {

        int H = 9;
        int M = 15;

        System.out.println(findAngle(H,M));


    }

    private static float findAngle(int H, int M){
        if(H<0 || M<0 || H>12 || M>60)
            return -1;
        float hour = ((H%12)+((float)M/60))*30; //30 degree -- 360/12 = 30 .. hour hand moves 30 degree every hour
        float minute = (M*6); // 6 degree -- 360/60 --- minute hand moves 6 degree every minute

        float angle = Math.abs(hour-minute);
        if(angle>180){
            angle = 360-angle;
        }

        return angle;


    }


}

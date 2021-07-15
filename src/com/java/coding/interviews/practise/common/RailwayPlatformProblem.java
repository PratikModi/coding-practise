package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class RailwayPlatformProblem {

    public static void main(String[] args) {
        int[] arrival = {900,940,950,1100,1500,1800};
        int[] departure= {910,1200,1120,1130,1900,2000};
        System.out.println(findMinimumPlatform(arrival,departure,arrival.length));
    }

    public static int findMinimumPlatform(int[] arrival, int[] departure, int n){
        int result=1;
        int platform_need=1;
        int i=1,j=0;
        Arrays.sort(arrival);
        Arrays.sort(departure);
        while(i<n && j<n){
            if(arrival[i] <= departure[j]){
                platform_need++;
                i++;
            }else{
                platform_need--;
                j++;
            }
            result=Math.max(result,platform_need);
        }
        return result;
    }

}

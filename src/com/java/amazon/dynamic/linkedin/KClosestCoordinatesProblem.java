package com.java.amazon.dynamic.linkedin;

/**
 * Created by Pratik1 on 15-06-2020.
 */

import java.util.*;

/**
 * This problem was asked by LinkedIn.
 Given a list of points, a central point, and an integer k,
 find the nearest k points from the central point.

 For example,
 given the list of points [(0, 0), (5, 4), (3, 1)],
 the central point (1, 2), and k = 2, return [(0, 0), (3, 1)].
 */
public class KClosestCoordinatesProblem {

    public static void main(String[] args) {
        int[][] coordinates = new int[][] {{0,0},{5,4},{3,1}};
        int[] central = new int[] {1,2};
        int K=2;
        System.out.println(findKClosestPoint(coordinates,central,K));
    }

    public static List<List<Integer>> findKClosestPoint(int[][] coordinates,int[] central,int K){
        List<List<Integer>> result = new ArrayList<>();
        Map<Double,List<Integer>> map = new TreeMap<>();
        if(coordinates==null || coordinates.length==0)
            return result;
        if(central==null || central.length<2)
            return result;
        for(int i=0;i<coordinates.length;i++){
            double key = Math.sqrt(Math.pow((central[0]-coordinates[i][0]),2)+Math.pow((central[1]-coordinates[i][1]),2));
            if(map.containsKey(key)){
                map.get(key).add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(key,list);
            }
        }
        map.entrySet().stream().forEach(e->{
            for (int i = 0; i < e.getValue().size(); i++) {
                result.add(Arrays.asList(coordinates[e.getValue().get(i)][0],coordinates[e.getValue().get(i)][1]));
            }
        });
        return result.subList(0,K);
    }

}

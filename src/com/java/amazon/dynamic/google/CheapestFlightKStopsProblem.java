package com.java.amazon.dynamic.google;

import java.util.*;

/**
 * There are n cities connected by m flights. Each flight starts from city u and
 * arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and
 * the destination dst, your task is to find the cheapest price from src to dst
 * with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 */

public class CheapestFlightKStopsProblem {

    public static void main(String[] args) {
        int[][] flights = new int[][]
                {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(cheapestFlight(flights,0,2,1));
    }

    public static int cheapestFlight(int[][] flights,int src,int dest,int K){
        if(flights==null || flights.length==0)
            return 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] flight:flights){
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new int[]{flight[1],flight[2]});
        }
        PriorityQueue<int[]> PQ = new PriorityQueue<>((e1,e2)->e1[1]-e2[1]);
        PQ.offer(new int[]{src,0,K});
        while(!PQ.isEmpty()){
            int[] F = PQ.poll();
            int curr = F[0];
            int cost = F[1];
            int stop = F[2];
            if(curr==dest)
                return cost;
            if(stop>=0){
                if(!map.containsKey(curr))
                    continue;
                for(int[] next:map.get(curr)){
                    PQ.add(new int[]{next[0],cost+next[1],stop-1});
                }
            }
        }
        return -1;
    }

}

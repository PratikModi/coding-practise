package com.java.amazon.dynamic.uber;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 */
public class BusRouteProblem {

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        System.out.println(numBusesToDestination(routes,1,6));
        routes= new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        System.out.println(numBusesToDestination(routes,15,12));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        int result=0;
        if(routes==null || routes.length==0)
            return -1;
        Map<Integer, List<Integer>> busInStops = new HashMap<>();
        for(int bus=0;bus<routes.length;bus++){
            for(int i=0;i<routes[bus].length;i++){
                int route = routes[bus][i];
                busInStops.putIfAbsent(route,new ArrayList<>());
                busInStops.get(route).add(bus);
            }
        }
        System.out.println(busInStops);

        Queue<Integer> Q = new LinkedList<>();
        Q.add(source);

        Set<Integer> visitedBuses = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();
        visitedStops.add(source);

        while(!Q.isEmpty()){
            int size=Q.size();
            for(int i=0;i<size;i++) {
                int stop = Q.remove();
                if (stop == target)
                    return result;
                for(int bus :busInStops.get(stop)) {
                    if (visitedBuses.contains(bus))
                        continue;
                    visitedBuses.add(bus);
                    for(int stp : routes[bus]){
                        if(visitedStops.contains(stp))
                            continue;
                        visitedStops.add(stp);
                        Q.add(stp);
                    }
                }
            }
            result++;

        }


        return -1;
    }

}

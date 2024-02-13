package com.java.coding.interviews.practise.second.uber;

import java.util.*;

public class BusRouteProblem {

    public static int numberBusToDestination(int[][] routes, int source, int destination){
        int result=0;
        if(routes==null || routes.length==0)
            return result;
        Map<Integer, List<Integer>> busPerStops = new HashMap<>();
        for(int bus=0;bus< routes.length;bus++){
            for(int i=0;i<routes[bus].length;i++){
                int route = routes[bus][i];
                busPerStops.putIfAbsent(route, new ArrayList<>());
                busPerStops.get(route).add(bus);
            }
        }
        System.out.println(busPerStops);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        Set<Integer> visitedBus = new HashSet<>();
        Set<Integer> visitedStop = new HashSet<>();
        visitedStop.add(source);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int stop = queue.remove();
                if(stop==destination)
                    return result;
                for(int bus : busPerStops.get(stop)){
                    if(visitedBus.contains(bus))
                        continue;
                    visitedBus.add(bus);
                    for(int stp : routes[bus]){
                        if(visitedStop.contains(stp))
                            continue;
                        visitedStop.add(stp);
                        queue.add(stp);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        System.out.println(numberBusToDestination(routes,1,6));
        routes= new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        System.out.println(numberBusToDestination(routes,15,12));
    }

}

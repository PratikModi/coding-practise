package com.java.coding.interviews.practise.common;

import java.util.*;

public class BusRouteProblem {

    public static int numberOfBusToDestination(int[][] routes, int source, int destination){
        int result=0;
        if(routes==null || routes.length==0)
            return -1;
        Map<Integer, List<Integer>> busPerStop = new HashMap<>();
        for(int i=0;i<routes.length;i++){
            for(int stop : routes[i]){
                busPerStop.putIfAbsent(stop,new ArrayList<>());
                busPerStop.get(stop).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        Set<Integer> visitedStop = new HashSet<>();
        Set<Integer> visitedBus = new HashSet<>();
        visitedStop.add(source);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int stop = queue.remove();
                if(stop==destination)
                    return result;
                for(int bus : busPerStop.get(stop)){
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
                result++;
            }
        }

        return -1;
    }
}

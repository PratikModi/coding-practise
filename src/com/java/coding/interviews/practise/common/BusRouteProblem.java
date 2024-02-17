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
            }
            result++;
        }
        return -1;
    }

    public static int findCheapestRoute(int[][] routes, int source, int destination){
        if(routes==null || routes.length==0)
            return -1;
        Map<Integer,List<int[]>> busRuteMap = new HashMap<>();
        for(int[] route : routes){
            busRuteMap.putIfAbsent(route[0],new ArrayList<>());
            busRuteMap.get(route[0]).add(new int[]{route[1],route[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2)->e1[1]-e2[1]);
        pq.offer(new int[]{source,0});
        while(!pq.isEmpty()){
            int[] entry = pq.remove();
            int current = entry[0];
            int cost = entry[1];
            if(current==destination)
                return cost;
            if(!busRuteMap.containsKey(current))
                continue;
            for(int[] next : busRuteMap.get(current)){
                pq.add(new int[]{next[0],cost+next[1]});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        System.out.println(numberOfBusToDestination(routes,1,6));
        routes= new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        System.out.println(numberOfBusToDestination(routes,15,12));
        routes = new int[][]{{1,2,100},{2,3,100},{1,3,100}};
        System.out.println(findCheapestRoute(routes,1,3));
    }
}

package com.java.coding.interviews.practise.second.uber;

import java.util.*;

public class EnemyProblem {

    public static void main(String[] args) {
        List<int[]> edges = List.of(new int[]{0,1}, new int[]{1,2},new int[]{3,4}, new int[]{2,5}, new int[]{2,3});
        List<int[]> enemies = List.of(new int[]{1,4}, new int[]{1,5});
        List<Boolean> result = findNodeResult(edges,enemies);
        System.out.println(result);
    }

    public static List<Boolean> findNodeResult(List<int[]> edges, List<int[]> enemies){
        Map<Integer,Set<Integer>> graph = new HashMap<>();
        List<Boolean> result = new ArrayList<>();
        for(int[] edge : edges){
            result.add(updateGraph(graph,enemies,edge));
        }
        return result;
    }

    private static boolean updateGraph(Map<Integer,Set<Integer>> graph, List<int[]> enemies, int[] edge){
        int start = edge[0];
        int end = edge[1];
        graph.putIfAbsent(start,new HashSet<>());
        graph.putIfAbsent(end,new HashSet<>());
        graph.get(start).add(end);
        graph.get(end).add(start);
        for(int[] enemy : enemies){
            if(isConnected(graph,enemy))
                return false;
        }
        return true;
    }

    private static boolean isConnected(Map<Integer, Set<Integer>> graph, int[] enemy){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int start = enemy[0];
        int end = enemy[1];

        queue.add(start);
        while(!queue.isEmpty()){
            int current = queue.remove();
            if(current==end)
                return true;
            if(!visited.contains(current)){
                visited.add(current);
                for(int neighbour : graph.getOrDefault(current,new HashSet<>())){
                    if(!visited.contains(neighbour)){
                        //visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }
        return false;
    }
}

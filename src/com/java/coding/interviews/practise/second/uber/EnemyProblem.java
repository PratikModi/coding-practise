package com.java.coding.interviews.practise.second.uber;

import java.util.*;

public class EnemyProblem {

    public static void main(String[] args) {
        List<int[]> edges = List.of(new int[]{0,1}, new int[]{1,2},new int[]{3,4}, new int[]{2,5}, new int[]{2,3});
        List<int[]> enemies = List.of(new int[]{1,4}, new int[]{1,5});
        List<Boolean> result = findNodeResult(edges,enemies);
        System.out.println(result);
        result = canConnect(edges,enemies,6);
        System.out.println(result);
    }

    public static List<Boolean> canConnect(List<int[]> edges, List<int[]> enemies, int nodes){
        List<Boolean> result = new ArrayList<>();
        if(edges==null || enemies==null || edges.size()==0 || enemies.size()==0)
            return result;
        int[] parent = new int[nodes];
        for(int i=0;i<nodes;i++){
            parent[i]=i;
        }

        for(int[] edge : edges){
            addEdge(edge[0],edge[1],parent);
            result.add(true);
            for(int[] enemy : enemies){
                if(areInSameGroup(enemy[0],enemy[1],parent))
                    result.set(result.size()-1,false);
            }
        }
        return result;
    }

    private static void addEdge(int p, int q, int[] parent){
        union(p,q,parent);
    }

    private static void union(int p, int q, int[] parent){
        int parentP = findParent(p, parent);
        int parentQ = findParent(q, parent);
        if(parentP<parentQ)
            parent[q]=parentP;
        else
            parent[p]=parentQ;
    }

    private static boolean areInSameGroup(int p, int q, int[] parent){
        int parentP = findParent(p, parent);
        int parentQ = findParent(q, parent);

        return parentP==parentQ;
    }

    private static int findParent(int p, int[] parent){
        if(parent[p]==p)
            return p;
        parent[p] = findParent(parent[p],parent);
        return parent[p];
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

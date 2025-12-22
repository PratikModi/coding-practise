package com.java.coding.interviews.practise.point72;
/**
 * Shortest Path between two nodes.
 *
 * Time Complexity: O(V+E)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathBetweenTwoNodesProblem {

    public static void main(String[] args) {
        int vertices = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(5);
        int source =0;
        System.out.println(shortestPath(vertices,adj,source,5));
    }

    public static int shortestPath(int n, List<List<Integer>> adj, int src, int dest){
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src,0});
        visited[src]=true;
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int curr = node[0], dist = node[1];
            if(curr==dest) return dist;
            for(int neighbour : adj.get(curr)){
                if(!visited[neighbour]){
                    visited[neighbour]=true;
                    queue.offer(new int[]{neighbour,dist+1});
                }
            }
        }
        return -1;
    }

}

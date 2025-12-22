package com.java.coding.interviews.practise.point72;

/**
 * 🧩 Problem
 * Shortest Path in an Unweighted Graph
 *
 * Given an unweighted graph and a source node, find the shortest distance from the source to every other node.
 *
 * ⸻
 */

import java.util.*;

public class ShortestPathToAllNodesProblem {

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
        shortestPath(vertices,adj,source);
    }

    public static void shortestPath(int vertices, List<List<Integer>> adj, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, -1); // -1 means unvisited

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        distance[source] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adj.get(node)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        // Print result
        for (int i = 0; i < vertices; i++) {
            System.out.println("Node " + i + " -> Distance: " + distance[i]);
        }
    }
}

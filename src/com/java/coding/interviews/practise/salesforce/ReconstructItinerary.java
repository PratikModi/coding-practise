package com.java.coding.interviews.practise.salesforce;

import java.util.*;

/**
 * Leetcode 332. Reconstruct Itinerary
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * All the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 * ⏱ Time Complexity
 * Building adjacency list:
 * 	•	O(E log E), because inserting into min-heap for sorting.
 * DFS traversal:
 * 	•	Each edge is visited once → O(E log E) due to heap polling.
 * Overall Time:
 *       O(E log E)
 * where E = number of tickets.
 * Overall Space:
 *   O(E)
 */
public class ReconstructItinerary {
    private static Map<String, PriorityQueue<String>> map = new HashMap<>();
    private static LinkedList<String> result = new LinkedList<>();
    public static List<String> findItinerary(List<List<String>> tickets) {

        for(List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dest = ticket.get(1);
            map.computeIfAbsent(src, k->new PriorityQueue<String>()).offer(dest);
        }
        dfs("JFK");
        return result;
    }

    private static void dfs(String airport){
        PriorityQueue<String> adj = map.get(airport);
        while(adj!=null && !adj.isEmpty()){
            String next = adj.poll();
            dfs(next);
        }
        result.addFirst(airport);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(List.of("MUC","LHR"));
        tickets.add((List.of("JFK","MUC")));
        tickets.add((List.of("SFO","SJC")));
        tickets.add(List.of("LHR","SFO"));
        System.out.println(findItinerary(tickets));
    }
}

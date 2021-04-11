package com.java.amazon.dynamic.google;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
 * order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical
 * order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class ReConstructItineraryProblem {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> t1 = Arrays.asList("JFK","SFO");
        List<String> t2 = Arrays.asList("JFK","ATL");
        List<String> t3 = Arrays.asList("SFO","ATL");
        List<String> t4 = Arrays.asList("ATL","JFK");
        List<String> t5 = Arrays.asList("ATL","SFO");
        tickets.add(t1);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        tickets.add(t5);
        System.out.println(reconstructItinerary(tickets));

    }

    public static List<String> reconstructItinerary(List<List<String>> tickets){
        List<String> result = new ArrayList<>();
        Map<String, PriorityQueue<String>> map = new HashMap();
        for(List<String> ticket : tickets){
            PriorityQueue<String> PQ = map.getOrDefault(ticket.get(0),new PriorityQueue<>(Comparator.naturalOrder()));
            //map.putIfAbsent(ticket.get(0),PQ);
            PQ.offer(ticket.get(1));
            map.put(ticket.get(0),PQ);
        }
        System.out.println(map);
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while(!stack.isEmpty()){
            String top = stack.peek();
            if(map.get(top)==null || map.get(top).isEmpty()){
                result.add(top);
                stack.pop();
            }else{
                stack.push(map.get(top).poll());
            }
        }
        Collections.reverse(result);
        return result;
    }
}

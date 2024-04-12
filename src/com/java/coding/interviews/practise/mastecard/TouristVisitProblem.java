package com.java.coding.interviews.practise.mastecard;

import java.util.*;

/**
 * A tourist James wants to visit landmarks which are labelled 1...N in a city. The landmarks are connected by K bus routes which cost money.
 * James has M dollars initially and wants to visit maximum number of landmarks, starting from landmark 1.
 * As he is new to the city, he retraces his steps to return home. Find the maximum number of landmarks James can visit.
 *
 * Constraints:
 * 1 <= N,K <= 100
 * 1 <= M, cost of bus route b/w two landmarks <= 10^9
 *
 * Example:
 * N = 5, K = 4, M = 120
 * Edges (landmark1, landmark2, cost) :
 * 1, 2, 10
 * 2, 3, 20
 * 3, 4, 30
 * 4, 5, 40
 *
 * Output:
 * 4
 * Explanation :
 * At 1, james has 120 dollars.
 * From 1 to 2, costs 10.
 * From 2 to 3, costs 20
 * From 3 to 4, costs 30.
 * From 4 to 5, costs 40.
 * Given james has 120 dollars only, he cant visit landmark 5 as he will run out of money and cannot retrace his steps back home.
 * However he can follow route 1 2 3 4 3 2 1 (cost = 2*(10 + 20 + 30) = 120) with 120 dollars. Hence max 4 landmarks can be visited.
 */
public class TouristVisitProblem {

    public static void main(String[] args) {
        System.out.println(findMaxStops(5,4,10));
    }

    public static int findMaxStops(int N, int K, int M){
        if(N<=0 || K<=0 || M<=0)
            return 0;
        Map<Integer, List<Integer>> graph = new TreeMap<>();
        int city=1;
        int stops=0;
        int cost=10;
        for(int i=0;i<K;i++){
            graph.put(city,List.of(++city,cost));
            cost+=10;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1,0});
        while(!queue.isEmpty()){
            int[] S = queue.poll();
            int curr = S[0];
            int rCost = S[1];
            System.out.println(curr+"--"+rCost);
            if(rCost==M/2)
                return curr;
            if(rCost>M/2)
                return curr-1;
            stops=curr;
            if(!graph.containsKey(curr))
                continue;
            List<Integer> coordinates = graph.get(curr);
            queue.add(new int[]{coordinates.get(0),rCost+coordinates.get(1)});
        }
        return stops;
    }

}

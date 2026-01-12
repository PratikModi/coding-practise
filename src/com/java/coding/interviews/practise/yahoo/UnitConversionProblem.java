package com.java.coding.interviews.practise.yahoo;

import java.util.*;

/**
 * You are given a list of unit conversion equations and their corresponding values.
 * Each equation A -> B = value means:
 * 1 A = value B
 * You are also given queries. For each query, return the converted value.
 * If the conversion is not possible, return -1.0.
 *
 * equations = [
 *   ["foot", "inch"],
 *   ["inch", "centimeter"],
 *   ["meter", "centimeter"]
 * ]
 *
 * values = [12.0, 2.54, 100.0]
 *
 * queries = [
 *   ["foot", "centimeter"],
 *   ["meter", "inch"],
 *   ["inch", "foot"],
 *   ["yard", "meter"]
 * ]
 *
 * [30.48, 39.37, 0.0833, -1.0]
 * ⏱ Complexity
 * Build Graph: O(E)
 * Each Query: O(V + E)
 * Total: O(Q × (V + E))
 */
public class UnitConversionProblem {

    static class Pair{
        String unit;
        double value;

        Pair(String unit, double value){
            this.unit=unit;
            this.value=value;
        }
    }

    public static void main(String[] args) {
        List<List<String>> equations = List.of(
                List.of("foot", "inch"),
                List.of("inch", "centimeter"),
                List.of("meter", "centimeter")
        );

        double[] values = {12.0, 2.54, 100.0};

        List<List<String>> queries = List.of(
                List.of("foot", "centimeter"),
                List.of("meter", "inch"),
                List.of("inch", "foot"),
                List.of("yard", "meter")
        );

        System.out.println(Arrays.toString(
                conversion(equations, values, queries)
        ));
    }

    public static double[] conversion(List<List<String>> equations, double[] values, List<List<String>> queries){
        double[] result = new double[queries.size()];
        Map<String, List<Pair>> graph = new HashMap<>();
        for(int i=0;i<equations.size();i++){
            List<String> eq = equations.get(i);
            String from = eq.get(0);
            String to = eq.get(1);
            double value = values[i];
            graph.computeIfAbsent(from, k->new ArrayList<>()).add(new Pair(to,value));
            graph.computeIfAbsent(to, k->new ArrayList<>()).add(new Pair(from, 1/value));
        }
        for(int i=0;i<queries.size();i++){
            List<String> query = queries.get(i);
            result[i] = bfs(graph,query.get(0),query.get(1));
        }
        return result;
    }

    private static double bfs(Map<String, List<Pair>> graph, String src, String dest){
        if(!graph.containsKey(src) || !graph.containsKey(dest))
            return -1.0;
        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair(src,1.0));
        visited.add(src);
        while(!queue.isEmpty()) {
            Pair current = queue.poll();
            if (current.unit.equals(dest)) {
                return current.value;
            }
            for (Pair neighbour : graph.get(current.unit)) {
                if (!visited.contains(neighbour.unit)) {
                    String next = neighbour.unit;
                    double value = current.value * neighbour.value;
                    queue.offer(new Pair(next, value));
                    visited.add(neighbour.unit);
                }
            }
        }
        return -1.0;
    }


}

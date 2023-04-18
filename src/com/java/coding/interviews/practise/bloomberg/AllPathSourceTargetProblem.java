package com.java.coding.interviews.practise.bloomberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 *
 *
 * Example 1:
 *
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 *
 * Example 2:
 *
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 */
//Time Complexity  : O(2^n*n)
//Space Complexity : O(n)
public class AllPathSourceTargetProblem {

    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(allPathSourceTarget(graph));
    }

    public static List<List<Integer>> allPathSourceTarget(int[][] graph){
        List<List<Integer>> allPaths = new ArrayList<>();
        if(graph==null || graph.length==0)
            return allPaths;
        Map<Integer,List<Integer>> edges = new HashMap<>();
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                edges.computeIfAbsent(i,val->new ArrayList<>()).add(graph[i][j]);
            }
        }
        helper(allPaths,edges,new ArrayList<Integer>(),0,graph.length-1);
        return allPaths;
    }

    private static void helper(List<List<Integer>> allPaths, Map<Integer,List<Integer>> edges, List<Integer> currPaths, int source, int destination){
        currPaths.add(source);
        if(source==destination){
            allPaths.add(currPaths);
            return;
        }
        List<Integer> nodes = edges.get(source);
        if(nodes==null) return;
        for(int node : nodes){
            helper(allPaths,edges,new ArrayList<>(currPaths),node,destination);
        }
    }

}

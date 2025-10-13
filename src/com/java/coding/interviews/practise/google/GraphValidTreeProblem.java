package com.java.coding.interviews.practise.google;

/**
 * 261. Graph Valid Tree
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi]
 * indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 *
 * 🌳 A Graph is a Tree if:
 * It has exactly n - 1 edges (otherwise, either disconnected or has a cycle).
 * It is fully connected (every node reachable from any other node).
 *
 * 💡 Idea
 *
 * Each node starts in its own set.
 * For each edge (u, v):
 * If u and v already belong to the same set → there’s a cycle → return false.
 * Otherwise, union the sets.
 * After processing all edges:
 * Ensure total edges = n - 1 (no disconnected components).
 *
 * ⏱ Complexity
 * Type	Complexity	Explanation
 * Time	O(E * α(N))	almost linear (α = inverse Ackermann, very small)
 * Space	O(N)	for parent array
 */
public class GraphValidTreeProblem {

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int n =5;
        System.out.println(validTree(n,edges));
        edges = new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}};
        System.out.println(validTree(n,edges));
    }

    public static boolean validTree(int n, int[][] edges) {
        if(edges.length!=n-1) return false;
        int[] parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i; // initially each node is its own parent
        }
        for(int[] edge : edges){
            int rootA = find(parent,edge[0]);
            int rootB = find(parent,edge[1]);

            if(rootA==rootB){ //Cycle Detected
                return false;
            }
            parent[rootA]=rootB;
        }
        return true;
    }

    private static int find(int[] parent, int x){
        if(parent[x]!=x){
            parent[x] = find(parent,parent[x]);
        }
        return parent[x];
    }

}

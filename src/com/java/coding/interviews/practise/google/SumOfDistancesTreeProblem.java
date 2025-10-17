package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 834. Sum of Distances in Tree
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge
 * between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 * ⚙️ Approach — Two DFS Strategy
 *
 * We use two DFS traversals — often called post-order and pre-order traversals.
 *
 * 🧩 Step 1: Build Tree
 * Use an adjacency list.
 *
 * 🧩 Step 2: Post-order DFS (Bottom-up)
 * For each node:
 * 	•	Compute the count of nodes in its subtree (including itself)
 * 	•	Compute the sum of distances from this node to all nodes in its subtree
 *
 * 🧩 Step 3: Pre-order DFS (Top-down)
 * Now we propagate results from root to children.
 * When we move from parent to child, distances change systematically:
 * 	•	All nodes in the child’s subtree become 1 closer to the child.
 * 	•	All other nodes (outside that subtree) become 1 farther.
 *
 * 	ans[child] = ans[parent] - count[child] + (N - count[child])
 * 🧠 Complexity Analysis
 * Step                     Time       Space
 * Build Graph          O(N)        O(N)
 * Postorder DFS      O(N)        O(N)
 * Preorder DFS       O(N)        O(N)
 * Total                  O(N)        O(N)
 */
public class SumOfDistancesTreeProblem {

    static int N;
    static int[] count;
    static int[] ans;
    static List<List<Integer>> graph;

    public static void main(String[] args) {
        int[][] edges = new int[][] {{0,1},{0,2},{2,3},{2,4},{2,5}};
        int n=6;
        System.out.println(Arrays.toString(sumOfDistancesInTree(n,edges)));
    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        N=n;
        count = new int[n];
        ans = new int[n];
        graph = new ArrayList<>();

        // Step 1: Build adjacency list
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // Step 2: Postorder DFS from root (0)
        postOrder(0,-1);
        // Step 3: Preorder DFS to adjust results for all nodes
        preOrder(0,-1);
        return ans;
    }

    private static void postOrder(int node, int parent){
        count[node]=1; //itself
        for(int child : graph.get(node)){
            if(child==parent)continue;
            postOrder(child,node);
            count[node]+=count[child];
            //ans[node] += (sum of distances from child to its subtree)
            //+ (1 for each node in that subtree)
            ans[node]+=ans[child]+count[child];
        }
    }

    private static void preOrder(int node, int parent){
        for(int child : graph.get(node)){
            if(child==parent) continue;
            ans[child]= ans[node] -count[child] +(N-count[child]);
            preOrder(child,node);
        }
    }

}
